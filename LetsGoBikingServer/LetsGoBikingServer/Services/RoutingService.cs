using LetsGoBikingLibrary2.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net;
using System.Reflection.Emit;
using System.ServiceModel;
using System.Threading.Tasks;
using System.Web;
using System.Web.Routing;
using LetsGoBikingProxy.Services;
using LetsGoBikingServer.Utils;
using LetsGoBikingServer.Exceptions;
using System.Web.ModelBinding;
using System.Collections;
using System.Device.Location;
using LetsGoBikingServer.Manager;

namespace LetsGoBikingServer.Services
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]

    public class RoutingService : IRoutingService
    {
        private readonly HttpClient _httpClient = new HttpClient();
        private readonly ActiveMQService _activeMQService;
        private readonly ItineraryCache itineraryCache = ItineraryCache.GetInstance();
        //boolean pour savoir is activeMQ est lancé ou non
        private bool isActiveMQ = true;

        private IStationProxyService _iStationService = StationProxyService.GetInstance();


        string apiKeyOpenStreet = "5b3ce3597851110001cf62481d3c3c09de44442abe0e719a6ce409e1";
        string apiKeyJcDecaux = "0484963fbd484dfeb5bf83031ef743273bf62fbc";
        public RoutingService() {
            //checkez si activeMQ est lancé
            try
            {
                _activeMQService = new ActiveMQService("tcp://localhost:61616");

            }catch(Exception e)
            {
                isActiveMQ = false;
                Console.WriteLine("ActiveMQ n'est pas lancé");
            }

            _httpClient.DefaultRequestHeaders.Add("User-Agent", "LetsGoBkingLeo");


        }

        public async Task<Position> GetPositionAsync(string address)
        {
            string requestUri = $"https://nominatim.openstreetmap.org/search?format=json&q={address}";
            var response = await _httpClient.GetAsync(requestUri);
            response.EnsureSuccessStatusCode();

            var jsonResponse = await response.Content.ReadAsStringAsync();
            Position[] locations = JsonConvert.DeserializeObject<Position[]>(jsonResponse); 
     
            
                
            return locations.Length > 0 ? locations[0] : throw new NoPositionFoundException("L'adresse donnée est incorrecte"); // on retourne le [0] soit la location avec la class la plus élevée 
        }


        


        public async Task<RouteResponse> GetRouteAsync(double startLat, double startLon, double endLat, double endLon, string typeRoute)
        {
            string profile;

            if (typeRoute == "cycling")
            {
                profile = "cycling-regular"; // Profil pour le cyclisme
            }
            else if (typeRoute == "walking")
            {
                profile = "foot-walking"; // Profil pour la marche à pied
            }
            else
            {
                profile = "driving-car"; // Profil par défaut pour la voiture
            }

            string requestUri = $"https://api.openrouteservice.org/v2/directions/{profile}?api_key={apiKeyOpenStreet}&start={startLon.ToString(System.Globalization.CultureInfo.InvariantCulture)},{startLat.ToString(System.Globalization.CultureInfo.InvariantCulture)}&end={endLon.ToString(System.Globalization.CultureInfo.InvariantCulture)},{endLat.ToString(System.Globalization.CultureInfo.InvariantCulture)}";
           
            //affiche uri
            var response = await _httpClient.GetAsync(requestUri);
            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();

                // Désérialiser la réponse JSON
                var routeData = JsonConvert.DeserializeObject<RouteResponse>(jsonResponse);

                

                // Vérifier si routeData et ses propriétés sont non nulles
                if (routeData?.Features != null)
                {
                    Converter converter = new Converter();
                    var coordinates = converter.ExtractCoordinatesFromJson(jsonResponse);

                    // Vérifier si les coordonnées extraites sont non nulles et non vides
                    if (coordinates != null && coordinates.Any())
                    {
                        converter.PopulateStepCoordinates(routeData, coordinates);
                    }
                    else
                    {
                        Console.WriteLine("Aucune coordonnée extraite du JSON.");
                        return null; // ou gérer autrement
                    }
                }
                else
                {
                    Console.WriteLine("La réponse de l'API ne contient pas les données attendues.");
                    return null; // ou gérer autrement
                }

                return routeData;
            }

            Console.WriteLine("Échec de la requête API.");
            return null;
        }


        public async Task<CompleteRoute> GetCompleteRouteAsync(string startAddress, string endAddress)
        {
            Position startPosition = await this.GetPositionAsync(startAddress);
            Position endPosition = await this.GetPositionAsync(endAddress);
            string startContract = null;
            string endContract = null;
            Station startStation = null;
            Station endStation = null;

            RouteResponse walkToStartStation = null;
            RouteResponse bikeRoute = null;
            RouteResponse walkToEnd = null;

            RouteResponse walkRouteItinerary = null;

            double totalTimeWalk = 0;
            double totalTimeClassicItinerary = 0;

            CompleteRoute completeRoute = null;
            
            if(this.itineraryCache.TryGetItinerary(startAddress, endAddress, out completeRoute))
            {
                Console.WriteLine("Itinéraire trouvé dans le cache");
                callActiveMQSendSteps(completeRoute);

                return completeRoute;
            }
            
            //s'il y a un contract proche de la position d'arrivée et de départ alors un itinéraire est possible
            //Si la distance entre la position et le user > 40km alors on ne peut pas faire l'itinéraire
            startContract = await GetClosestContractAsync(startPosition.Lat, startPosition.Lon);
            endContract = await GetClosestContractAsync(endPosition.Lat, endPosition.Lon);
            //on va faire l'itinéraire complet
                
            startStation = await GetClosestStationsAsync(startPosition.Lat, startPosition.Lon, 3, startContract);

            endStation = await GetClosestStationsAsync(endPosition.Lat, endPosition.Lon, 3, endContract);
            //contract
                 
            //j'ai les stations les plus proches vu qu'il ya des bike diso dans les 2 stations départ arrivées
            //maintenant on va faire l'itinéraire à pied pour aller jusqu'à la station de départ + vélo + à pied jusqu'à l'arrivé
            walkToStartStation = await this.GetRouteAsync(startPosition.Lat, startPosition.Lon, startStation.position.Lat, startStation.position.Lon, "walking");
            bikeRoute = await this.GetRouteAsync(startStation.position.Lat, startStation.position.Lon, endStation.position.Lat, endStation.position.Lon, "cycling");
            walkToEnd = await this.GetRouteAsync(endStation.position.Lat, endStation.position.Lon, endPosition.Lat, endPosition.Lon, "walking");
            List<RouteResponse> routes = new List<RouteResponse>();
            routes.Add(walkToStartStation);
            routes.Add(bikeRoute);
            routes.Add(walkToEnd);
            //total time itinery classic 3 steps
            totalTimeClassicItinerary = calculateTimeFromListRouteResponse(routes);

            //faire le trajet à pied
            //total time à pied du début à la fin
            walkRouteItinerary = await this.GetRouteAsync(startPosition.Lat, startPosition.Lon, endPosition.Lat, endPosition.Lon, "walking");
            totalTimeWalk = calculateTimeFromListRouteResponse(new List<RouteResponse> { walkRouteItinerary });

            //si le temps total à pied est < au temps total en vélo alors on fait l'itinéraire à pied
            //nom de méthode pour faire cela
            //Console.WriteLine("Temps total itinéraire classique : " + totalTimeClassicItinerary);
            //Console.WriteLine("Temps total itinéraire à pied : " + totalTimeWalk);
            //total distance

            completeRoute = getCompleteRouteFromTime(totalTimeClassicItinerary, totalTimeWalk, walkToStartStation, bikeRoute, walkToEnd, walkRouteItinerary);
                    
            completeRoute.startPosition = startPosition;
            completeRoute.endPosition = endPosition;
                    
            //si le chemin est un intinéraire classic alors on va mettre les stations de départ et d'arrivée
            if (completeRoute.BikeRoute != null)
            {
                completeRoute.StartStation= startStation;
                completeRoute.EndStation = endStation;

            }
                    

            this.itineraryCache.AddItinerary(startAddress,endAddress,completeRoute);

            callActiveMQSendSteps(completeRoute);

            
               
            

       

            
            return completeRoute;

            
        }

        public void callActiveMQSendSteps(CompleteRoute completeRoute)
        {
            try
            {
                this._activeMQService.CreateQueueSendSteps(completeRoute);
            }
            catch (Exception e)
            {
                Console.WriteLine("ActiveMQ est innaccessbile");
            }
        }

        public CompleteRoute getCompleteRouteFromTime(double totalTimeClassicItinerary, double totalTimeWalk, RouteResponse walkToStartStation, RouteResponse bikeRoute, RouteResponse walkToEnd, RouteResponse walkRouteItinerary)
        {
            if(totalTimeWalk < totalTimeClassicItinerary)
            {
                return new CompleteRoute { WalkToStartStation = walkRouteItinerary, BikeRoute = null, WalkToEnd = null };
            }
            else
            {
                return new CompleteRoute { WalkToStartStation = walkToStartStation, BikeRoute = bikeRoute, WalkToEnd = walkToEnd };
            }
        }

        //y'a une attribut dans la classe routeResponse qui s'appelle summary et qui contient le temps total

        public double calculateTimeFromListRouteResponse(List<RouteResponse> routes)
        {
            double result = 0;

            foreach (RouteResponse route in routes)
            {
                foreach (var feature in route.Features)
                {
                    foreach (var segment in feature.Properties.Segments)
                    {
                        foreach (var step in segment.Steps)
                        {
                            result += step.Duration;
                        }
                    }
                }
            }
            return result;
        }

        public async Task<string> GetClosestContractAsync(double userLatitude, double userLongitude)
        {
            List<Contract> listContract = await this._iStationService.GetAllContractsAsync();
            string closestContract = null;
            double closestDistance = double.MaxValue;
            Position userPosition = new Position { Lat = userLatitude, Lon = userLongitude };
            //Affichage pos user
            double distance = -1;

            foreach (var contract in listContract)
            {
                // Filtrer les contrats non valides ou les cas spéciaux
                if (string.IsNullOrEmpty(contract.name) || contract.name == "jcdecauxbike")
                {
                    continue;
                }

                var locationContract = await GetPositionAsync(contract.name + ", " + contract.country_code);


                if (locationContract != null)
                {
                    Position contractPosition = new Position { Lat = locationContract.Lat, Lon = locationContract.Lon };
                    distance = CalculateDistanceAsync(userPosition, contractPosition);

                    // Vérifiez si la distance est valide avant de comparer avec la distance la plus proche
                    if (distance >= 0 && distance < closestDistance)
                    {
                        closestDistance = distance;
                        closestContract = contract.name;
                    }
                    else if (distance == -1)
                    {
                        Console.WriteLine($"Erreur lors du calcul de la distance pour le contrat {contract.name}");
                    }
                }
            }

            if (closestDistance > 40000)
            {
                throw new DistanceTooGreatException("La distance entre votre position et le contract "+ closestContract+" le plus proche est trop grande :"+distance );
              
            }
            return closestContract;
        }

        public async Task<Station> GetClosestStationsAsync(double userLatitude, double userLongitude, int numberOfStations, string closestContract)
        {
            List<Station> listStation = await this._iStationService.GetAllStationsAsync(closestContract);
            Position userPosition = new Position { Lat = userLatitude, Lon = userLongitude };

            var distances = new Dictionary<Station, double>();

            foreach (var station in listStation)
            {
                if(station.available_bikes>0 && station.status.Equals("OPEN")){
                    Position stationPosition = new Position { Lat = station.position.Lat, Lon = station.position.Lon };
                    double distance = CalculateDistanceAsync(userPosition, stationPosition);
                    distances[station] = distance;
                    //Afficher distance pour chaque station
                }

            }
            //On récupère les stations les plus proches à vol d'oiseau
            var sortedStationsByBirdFly = distances.OrderBy(kvp => kvp.Value)
                                          .Select(kvp => kvp.Key)
                                          .Take(numberOfStations)
                                          .ToList();

            if (sortedStationsByBirdFly.Count == 0)
            {
                throw new NoStationFoundException("Aucune station n'a été trouvée à proximité de votre position");
            }
            //Ensuite on renvoie la station qui est rééllement la plus proche grâce à l'api ORS
            List<Station> sortedStationsByRealWalkingDistance = await SortedDistanceStationByWalking(sortedStationsByBirdFly, userPosition, "walking");

            
            Station closestStation = GetFirstStationWithAvailableBike(sortedStationsByRealWalkingDistance);


            return closestStation;
        }

        public async Task<List<Station>> SortedDistanceStationByWalking(List<Station> stations, Position userPosition, string profile)
        {
            var stationDistances = new List<(Station station, double distance)>();

            foreach (var station in stations)
            {
                RouteResponse walkRoute = await GetRouteAsync(userPosition.Lat, userPosition.Lon, station.position.Lat, station.position.Lon, profile);
                double totalTimeWalk = calculateTimeFromListRouteResponse(new List<RouteResponse> { walkRoute });

                stationDistances.Add((station, totalTimeWalk));
            }

            // Trier les stations en fonction de la distance de marche
            stationDistances.Sort((x, y) => x.distance.CompareTo(y.distance));

            // Récupérer les stations triées
            return stationDistances.Select(sd => sd.station).ToList(); //ça permet de récupérer la liste des stations triées
        }


        public Station GetFirstStationWithAvailableBike(List<Station> stations)
        {
            foreach (var s in stations)
            {
                if (s.available_bikes > 0 && s.status.Equals("OPEN"))
                {
                    return s;
                }
            }
            throw new NoStationFoundException("Pas de bike disponible dans les 3 stations les plus proches");
        }



      


        //calculer la distance entre 2 positions avec geoCoordinate
        public double CalculateDistanceAsync(Position start, Position end)
        {
            GeoCoordinate geoStart = new GeoCoordinate(start.Lat, start.Lon);
            GeoCoordinate geoEnd = new GeoCoordinate(end.Lat, end.Lon);
            return geoStart.GetDistanceTo(geoEnd);
        }

     





    }




}



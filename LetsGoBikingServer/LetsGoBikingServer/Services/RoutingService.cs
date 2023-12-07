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

namespace LetsGoBikingServer.Services
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]

    public class RoutingService : IRoutingService
    {
        private readonly HttpClient _httpClient = new HttpClient();
        private readonly ActiveMQService _activeMQService;

        private IStationProxyService _iStationService = StationProxyService.GetInstance();
        string apiKeyOpenStreet = "5b3ce3597851110001cf62481d3c3c09de44442abe0e719a6ce409e1";
        string apiKeyJcDecaux = "0484963fbd484dfeb5bf83031ef743273bf62fbc";
        public RoutingService() {
            _activeMQService = new ActiveMQService("tcp://localhost:61616", "LetGoBikingQueue");
            _httpClient.DefaultRequestHeaders.Add("User-Agent", "LetsGoBkingLeo");


        }

        public async Task<Position> GetPositionAsync(string address)
        {
            string requestUri = $"https://nominatim.openstreetmap.org/search?format=json&q={address}";
            var response = await _httpClient.GetAsync(requestUri);
            response.EnsureSuccessStatusCode();

            var jsonResponse = await response.Content.ReadAsStringAsync();
            Position[] locations = JsonConvert.DeserializeObject<Position[]>(jsonResponse); 
     
            
                
            return locations.Length > 0 ? locations[0] : null; // on retourne le [0] soit la location avec la class la plus élevée 
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

            RouteResponse walkRouteItinery = null;

            double totalTimeWalk = 0;
            double totalTimeClassicItinery = 0;

            CompleteRoute completeRoute = null;

            try
            {
                //s'il y a un contract proche de la position d'arrivée et de départ alors un itinéraire est possible
                //Si la distance entre la position et le user > 40km alors on ne peut pas faire l'itinéraire
                startContract = await GetClosestContractAsync(startPosition.Lat, startPosition.Lon);
                endContract = await GetClosestContractAsync(endPosition.Lat, endPosition.Lon);
                //on va faire l'itinéraire complet
                try
                {
                    startStation = await GetClosestStationsAsync(startPosition.Lat, startPosition.Lon, 1, startContract);

                    endStation = await GetClosestStationsAsync(endPosition.Lat, endPosition.Lon, 1, endContract);
                    //contract
                    Console.WriteLine("Contrat de départ : " + startContract);
                    Console.WriteLine("Contrat d'arrivée : " + endContract);
                    Console.WriteLine("Station de départ : " + startStation.name);
                    Console.WriteLine("Station d'arrivée : " + endStation.name);
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
                    totalTimeClassicItinery = calculateTimeFromListRouteResponse(routes);

                    //faire le trajet à pied
                    //total time à pied du début à la fin
                    walkRouteItinery = await this.GetRouteAsync(startPosition.Lat, startPosition.Lon, endPosition.Lat, endPosition.Lon, "walking");
                    totalTimeWalk = calculateTimeFromListRouteResponse(new List<RouteResponse> { walkRouteItinery });

                    //si le temps total à pied est < au temps total en vélo alors on fait l'itinéraire à pied
                    //nom de méthode pour faire cela
                    Console.WriteLine("Temps total itinéraire classique : " + totalTimeClassicItinery);
                    Console.WriteLine("Temps total itinéraire à pied : " + totalTimeWalk);
                    //total distance

                    completeRoute = getCompleteRouteFromTime(totalTimeClassicItinery, totalTimeWalk, walkToStartStation, bikeRoute, walkToEnd, walkRouteItinery);
                    if (completeRoute.BikeRoute != null)
                    {
                        SendStepsToActiveMQClassicItinery(walkToStartStation,bikeRoute,walkToEnd, startAddress, endAddress, startPosition, endPosition, startStation,endStation);

                    }
                    else
                    {
                        SendStepsToActiveMQWalkItinery(walkToStartStation, startAddress, endAddress, startPosition, endPosition, startStation,endStation);
                    }
                       


                }
                catch (NoStationFoundException e)
                {
                    Console.WriteLine(e.Message);
                    _activeMQService.SendMessage(e.Message);
                    return null;
                }
            }
            catch (DistanceTooGreatException e)
            {
                Console.WriteLine(e.Message);
                _activeMQService.SendMessage(e.Message);
                return null;
            }

            _activeMQService.SendMessage("Départ de l'itinéraire de " + startAddress);
            _activeMQService.SendMessage("Marche vers la station de vélo");
            _activeMQService.SendMessage("Trajet en vélo");
            _activeMQService.SendMessage("Marche vers la destination finale");
            _activeMQService.SendMessage("Arrivée à " + endAddress);

            return completeRoute;

            
        }

        public void SendStepsToActiveMQWalkItinery(RouteResponse walkToStartStation, string startAddress, string endAddress, Position startPosition, Position endPosition, Station startStation, Station endStation)
        {

        }

        private void SendStepsToActiveMQClassicItinery(RouteResponse walkToStartStation, RouteResponse bikeRoute, RouteResponse walkToEnd, string startAddress, string endAddress, Position startPosition, Position endPosition, Station startStation, Station endStation)
        {
        }

        public CompleteRoute getCompleteRouteFromTime(double totalTimeClassicItinery, double totalTimeWalk, RouteResponse walkToStartStation, RouteResponse bikeRoute, RouteResponse walkToEnd, RouteResponse walkRouteItinery)
        {
            if(totalTimeWalk < totalTimeClassicItinery)
            {
                return new CompleteRoute { WalkToStartStation = walkRouteItinery, BikeRoute = null, WalkToEnd = null };
            }
            else
            {
                return new CompleteRoute { WalkToStartStation = walkToStartStation, BikeRoute = bikeRoute, WalkToEnd = walkToEnd };
            }
        }

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

            var sortedStations = distances.OrderBy(kvp => kvp.Value)
                                          .Select(kvp => kvp.Key)
                                          .Take(numberOfStations)
                                          .ToList();
            //je voudrais faire un appel de fonction à une fonction qui renvoie la station qui a des vélos disponibles

            if(sortedStations.Count == 0)
            {
                throw new NoStationFoundException("Aucune station n'a été trouvée à proximité de votre position");
            }
            Station closestStation = GetFirstStationWithAvailableBike(sortedStations);


            return closestStation;
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



        public double CalculateDistanceAsync(Position start, Position end)
        {
            //Afficher les positions
         
            var R = 6371; // Rayon de la Terre en kilomètres
            var dLat = DegreesToRadians(end.Lat - start.Lat);
            var dLon = DegreesToRadians(end.Lon - start.Lon);
            var a = Math.Sin(dLat / 2) * Math.Sin(dLat / 2) +
                    Math.Cos(DegreesToRadians(start.Lat)) * Math.Cos(DegreesToRadians(end.Lat)) *
                    Math.Sin(dLon / 2) * Math.Sin(dLon / 2);
            var c = 2 * Math.Atan2(Math.Sqrt(a), Math.Sqrt(1 - a));
            double result = R * c * 1000;
            return result;
        }

        private double DegreesToRadians(double degrees)
        {
            return degrees * (Math.PI / 180);
        }





    }




}



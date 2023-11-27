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

namespace LetsGoBikingServer.Services
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]

    public class RoutingService : IRoutingService
    {
        private readonly HttpClient _httpClient = new HttpClient();
        private readonly ActiveMQService _activeMQService;

        private IStationProxyService _iStationService = new StationProxyService();
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
            if(profile == "cycling-regular") { 
                Console.WriteLine("On cherche un itinéraire en vélo : ");
                Console.WriteLine("Start : " + startLat + " " + startLon);
                Console.WriteLine("End : " + endLat + " " + endLon);
                Console.WriteLine(requestUri);
            }
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

                        //afficher toutes les coo des steps
                        foreach (var feature in routeData.Features)
                        {
                            foreach (var segment in feature.Properties.Segments)
                            {
                                foreach (var step in segment.Steps)
                                {
                                    Console.WriteLine("Step START : " + step.StartLatitude + " " + step.StartLongitude);
                                    Console.WriteLine("Step END : " + step.EndLatitude + " " + step.EndLongitude);
                                }
                            }
                        }
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

            string startContract = await GetClosestContractAsync(startPosition.Lat, startPosition.Lon);


            string endContract = await GetClosestContractAsync(endPosition.Lat, endPosition.Lon);


            List<Station> startStations = await GetClosestStationsAsync(startPosition.Lat, startPosition.Lon, 1, startContract);

            List<Station> endStations = await GetClosestStationsAsync(endPosition.Lat, endPosition.Lon, 1, endContract);

            Console.WriteLine("Start station : " + startStations.First().name);
            RouteResponse walkToStartStation = await this.GetRouteAsync(startPosition.Lat, startPosition.Lon, startStations.First().position.Lat, startStations.First().position.Lon, "walking");
            Console.WriteLine("walk");

            RouteResponse bikeRoute = await this.GetRouteAsync(startStations.First().position.Lat, startStations.First().position.Lon, endStations.First().position.Lat, endStations.First().position.Lon, "cycling");
            Console.WriteLine("bike");

            RouteResponse walkToEnd = await this.GetRouteAsync(endStations.First().position.Lat, endStations.First().position.Lon, endPosition.Lat, endPosition.Lon, "walking");
            Console.WriteLine("walkend");

            _activeMQService.SendMessage("Départ de l'itinéraire de " + startAddress);
            _activeMQService.SendMessage("Marche vers la station de vélo");
            _activeMQService.SendMessage("Trajet en vélo");
            _activeMQService.SendMessage("Marche vers la destination finale");
            _activeMQService.SendMessage("Arrivée à " + endAddress);

      

            return new CompleteRoute
            {
                WalkToStartStation = walkToStartStation,
                BikeRoute = bikeRoute,
                WalkToEnd = walkToEnd
            };
        }





        public async Task<string> GetClosestContractAsync(double userLatitude, double userLongitude)
        {
            List<Contract> listContract = await this._iStationService.GetAllContractsAsync();
            string closestContract = null;
            double closestDistance = double.MaxValue;
            Position userPosition = new Position { Lat = userLatitude, Lon = userLongitude };
            Console.WriteLine("On cherche le CONTRACT LE PLUS PROCHE DE LA POSITION DU USER : ");
            //Affichage pos user
            Console.WriteLine("User : " + userPosition.Lat + " " + userPosition.Lon);

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
                    double distance = CalculateDistanceAsync(userPosition, contractPosition);
                    
                    //Afficher nom contract et distance par rapport à la position du user
                    Console.WriteLine("Contract : " + contract.name + " " + distance);

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
            Console.WriteLine(closestContract);

            return closestContract;
        }

        public async Task<List<Station>> GetClosestStationsAsync(double userLatitude, double userLongitude, int numberOfStations, string closestContract)
        {
            List<Station> listStation = await this._iStationService.GetAllStationsAsync(closestContract);
            Position userPosition = new Position { Lat = userLatitude, Lon = userLongitude };

            var distances = new Dictionary<Station, double>();

            foreach (var station in listStation)
            {
                Position stationPosition = new Position { Lat = station.position.Lat, Lon = station.position.Lon };
                double distance = CalculateDistanceAsync(userPosition, stationPosition);
                distances[station] = distance;
            }

            var sortedStations = distances.OrderBy(kvp => kvp.Value)
                                          .Select(kvp => kvp.Key)
                                          .Take(numberOfStations)
                                          .ToList();

            return sortedStations;
        }



        public double CalculateDistanceAsync(Position start, Position arrival)
        {
            {
                const double R = 6371000; // Rayon de la Terre en mètres
                var φ1 = start.Lat * Math.PI / 180; // φ, λ en radians
                var φ2 = arrival.Lat * Math.PI / 180;
                var Δφ = (arrival.Lat - start.Lat) * Math.PI / 180;
                var Δλ = (arrival.Lon - start.Lon) * Math.PI / 180;

                var a = Math.Sin(Δφ / 2) * Math.Sin(Δφ / 2) +
                        Math.Cos(φ1) * Math.Cos(φ2) *
                        Math.Sin(Δλ / 2) * Math.Sin(Δλ / 2);
                var c = 2 * Math.Atan2(Math.Sqrt(a), Math.Sqrt(1 - a));

                return R * c; // Distance en mètres
            }
        }





        }




}



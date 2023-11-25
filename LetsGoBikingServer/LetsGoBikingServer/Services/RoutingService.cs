using LetsGoBikingServer.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Reflection.Emit;
using System.ServiceModel;
using System.Threading.Tasks;
using System.Web;
using System.Web.Routing;

namespace LetsGoBikingServer.Services
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]

    public class RoutingService : IRoutingService
    {
        private readonly HttpClient _httpClient = new HttpClient();
        private IStationService _iStationService;
        string apiKey = "5b3ce3597851110001cf62481d3c3c09de44442abe0e719a6ce409e1";
        public RoutingService() {
            _httpClient.DefaultRequestHeaders.Add("User-Agent", "LetsGoBkingLeo");
            _iStationService = new StationService();
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


        public async Task<double> CalculateDistanceAsync(Position start, Position arrival)
        {
            string requestUri = $"https://api.openrouteservice.org/v2/directions/driving-car?api_key={apiKey}&start={start.Lon.ToString(System.Globalization.CultureInfo.InvariantCulture)},{start.Lat.ToString(System.Globalization.CultureInfo.InvariantCulture)}&end={arrival.Lon.ToString(System.Globalization.CultureInfo.InvariantCulture)},{arrival.Lat.ToString(System.Globalization.CultureInfo.InvariantCulture)}";
            var response = await _httpClient.GetAsync(requestUri);

            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();
                var routeResponse = JsonConvert.DeserializeObject<RouteResponse>(jsonResponse);

                if (routeResponse.Features != null && routeResponse.Features.Any())
                {
                    return routeResponse.Features.First().Properties.Summary.Distance;
                }
            }

            return -1; // ou gérez l'erreur comme vous le souhaitez
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

            string requestUri = $"https://api.openrouteservice.org/v2/directions/{profile}?api_key={apiKey}&start={startLon.ToString(System.Globalization.CultureInfo.InvariantCulture)},{startLat.ToString(System.Globalization.CultureInfo.InvariantCulture)}&end={endLon.ToString(System.Globalization.CultureInfo.InvariantCulture)},{endLat.ToString(System.Globalization.CultureInfo.InvariantCulture)}";

            var response = await _httpClient.GetAsync(requestUri);
            if (response.IsSuccessStatusCode)
            {
                var jsonResponse = await response.Content.ReadAsStringAsync();
                var routeData = JsonConvert.DeserializeObject<RouteResponse>(jsonResponse);

                return routeData;
            }

            return null;
        }


        public async Task<CompleteRoute> GetCompleteRouteAsync(string startAddress, string endAddress)
        {
            Position startPosition = await this.GetPositionAsync(startAddress);
            Position endPosition = await this.GetPositionAsync(endAddress);
            Console.WriteLine("ici ");

            string startContract = await _iStationService.GetClosestContractAsync(startPosition.Lat, startPosition.Lon);
            string endContract = await _iStationService.GetClosestContractAsync(endPosition.Lat, endPosition.Lon);
            Console.WriteLine("ici2 ");


            List<Station> startStations = await _iStationService.GetClosestStationsAsync(startPosition.Lat, startPosition.Lon, 1);
            List<Station> endStations = await _iStationService.GetClosestStationsAsync(endPosition.Lat, endPosition.Lon, 1);
            Console.WriteLine("ici3 ");

            RouteResponse walkToStartStation = await this.GetRouteAsync(startPosition.Lat, startPosition.Lon, startStations.First().position.Lat, startStations.First().position.Lon, "walking");
            Console.WriteLine("ici4 ");

            RouteResponse bikeRoute = await this.GetRouteAsync(startStations.First().position.Lat, startStations.First().position.Lon, endStations.First().position.Lat, endStations.First().position.Lon, "cycling");
            Console.WriteLine("ici5 ");

            RouteResponse walkToEnd = await this.GetRouteAsync(endStations.First().position.Lat, endStations.First().position.Lon, endPosition.Lat, endPosition.Lon, "walking");
            Console.WriteLine("ici6 ");

            return new CompleteRoute
            {
                WalkToStartStation = walkToStartStation,
                BikeRoute = bikeRoute,
                WalkToEnd = walkToEnd
            };
        }




    }


}
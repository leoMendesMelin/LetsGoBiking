using LetsGoBikingServer.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
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
        string apiKey = "5b3ce3597851110001cf62481d3c3c09de44442abe0e719a6ce409e1";
        public RoutingService() {
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

    }
}
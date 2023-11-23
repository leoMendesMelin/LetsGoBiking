using LetsGoBikingServer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Threading.Tasks;
using System.Web;
using System.Net.Http;
using Newtonsoft.Json;
using System.Diagnostics.Contracts;
using LetsGoBikingServer.Services;

namespace LetsGoBikingServer.Services
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]
    public class StationService : IStationService
    {
        private readonly RoutingService _iRoutingService = new RoutingService();
        private readonly HttpClient _client = new HttpClient();
        private readonly string _apiKey = "0484963fbd484dfeb5bf83031ef743273bf62fbc";


        public async Task<List<Station>> GetAllStationsAsync(string contractName)
        {
            var response = await _client.GetAsync($"https://api.jcdecaux.com/vls/v1/stations?contract={contractName}&apiKey={_apiKey}");
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Station>>(content);
        }

        //get all contract
        public async Task<List<Models.Contract>> GetAllContractsAsync()
        {
            var response = await _client.GetAsync($"https://api.jcdecaux.com/vls/v1/contracts?apiKey={_apiKey}");
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Models.Contract>>(content);
        }


        public async Task<string> GetClosestContractAsync(double userLatitude, double userLongitude)
        {
            List<Models.Contract> listContract = await GetAllContractsAsync();

            string closestContract = null;
            double closestDistance = double.MaxValue;

            foreach (var contract in listContract)
            {
                // Filtrer les contrats non valides ou les cas spéciaux
                if (string.IsNullOrEmpty(contract.name) || contract.name == "jcdecauxbike")
                {
                    continue;
                }

                var locationContract = await _iRoutingService.GetPositionAsync(contract.name + ", " + contract.country_code);

                if (locationContract != null)
                {
                    Position contractPosition = new Position { Lat = locationContract.Lat, Lon = locationContract.Lon };
                    Position userPosition = new Position { Lat = userLatitude, Lon = userLongitude };
                    double distance = await _iRoutingService.CalculateDistanceAsync(userPosition, contractPosition);

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

            return closestContract;
        }



    }
}
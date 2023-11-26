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
            Console.WriteLine($"https://api.jcdecaux.com/vls/v1/contracts?apiKey={_apiKey}");
            response.EnsureSuccessStatusCode();
            var content = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Models.Contract>>(content);
        }






    }
}
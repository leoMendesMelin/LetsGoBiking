using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using LetsGoBikingLibrary2.Models;
using Newtonsoft.Json;
using System.Net.Http;
using System.ServiceModel;
using LetsGoBikingProxy.Cache;

namespace LetsGoBikingProxy.Services
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)] 
    public class StationProxyService : IStationProxyService
    {
        private readonly HttpClient _httpClient = new HttpClient();
        private readonly string _jcDecauxApiKey = "0484963fbd484dfeb5bf83031ef743273bf62fbc";
        private GenericProxyCache<List<Station>> _stationsCache = new GenericProxyCache<List<Station>>();
        private GenericProxyCache<List<Contract>> _contractsCache = new GenericProxyCache<List<Contract>>();
        private static readonly Lazy<StationProxyService> _instance = new Lazy<StationProxyService>(() => new StationProxyService());


        public static StationProxyService GetInstance()
        {
            return _instance.Value;
        }
        public async Task<List<Station>> GetAllStationsAsync(string contractName)
        {
            string cacheKey = $"Stations_{contractName}";
            return await _stationsCache.Get(cacheKey, async () =>
            {
                var response = await _httpClient.GetAsync($"https://api.jcdecaux.com/vls/v1/stations?contract={contractName}&apiKey={_jcDecauxApiKey}");
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<List<Station>>(content);
            });
        }

        public async Task<List<Contract>> GetAllContractsAsync()
        {
            string cacheKey = "Contracts";
            return await _contractsCache.Get(cacheKey, async () =>
            {
                var response = await _httpClient.GetAsync($"https://api.jcdecaux.com/vls/v1/contracts?apiKey={_jcDecauxApiKey}");
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<List<Contract>>(content);
            });
        }
    }
}

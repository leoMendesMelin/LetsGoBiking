using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Runtime.Caching;
using System.Net.Http;
using LetsGoBikingLibrary2.Models;
using System.Threading.Tasks;
using System.ServiceModel;

namespace LetsGoBikingProxy.Services
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)] 
    public class StationProxyService : IStationProxyService
    {
        private readonly HttpClient _httpClient = new HttpClient();
        private readonly string _jcDecauxApiKey = "0484963fbd484dfeb5bf83031ef743273bf62fbc";
        private MemoryCache _cache = new MemoryCache("StationProxyServiceCache");
        private static StationProxyService _instance;

        public static StationProxyService GetInstance()
        {
            if (_instance == null)
            {
                _instance = new StationProxyService();
            }
            return _instance;
        }

        public async Task<List<Station>> GetAllStationsAsync(string contractName)
        {
            string cacheKey = $"Stations_{contractName}";
            Console.WriteLine("Cache station: " + _cache.Contains(cacheKey));
            Console.WriteLine("Cache station cache key: " + cacheKey);

            if (!_cache.Contains(cacheKey))
            {
                Console.WriteLine("Cache miss pour les station du contract " + contractName);
                var response = await _httpClient.GetAsync($"https://api.jcdecaux.com/vls/v1/stations?contract={contractName}&apiKey={_jcDecauxApiKey}");
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var stations = JsonConvert.DeserializeObject<List<Station>>(content);

                CacheItemPolicy policy = new CacheItemPolicy { AbsoluteExpiration = DateTimeOffset.Now.AddMinutes(5) };
                _cache.Add(new CacheItem(cacheKey, stations), policy);

                return stations;
            }
            else
            {
                Console.WriteLine("Cache hit pour les stations du contract " + contractName);
                return (List<Station>)_cache.Get(cacheKey);
            }
        }

        public async Task<List<Contract>> GetAllContractsAsync()
        {
            string cacheKey = "Contracts";
            Console.WriteLine("Cache contract: " + _cache.Contains(cacheKey));

            if (!_cache.Contains(cacheKey))
            {
                Console.WriteLine("Cache miss pour les contracts");
                var response = await _httpClient.GetAsync($"https://api.jcdecaux.com/vls/v1/contracts?apiKey={_jcDecauxApiKey}");
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var contracts = JsonConvert.DeserializeObject<List<Contract>>(content);

                CacheItemPolicy policy = new CacheItemPolicy { AbsoluteExpiration = DateTimeOffset.Now.AddMinutes(10) };
                _cache.Add(new CacheItem(cacheKey, contracts), policy);

                return contracts;
            }
            else
            {
                return (List<Contract>)_cache.Get(cacheKey);
            }
        }
    }
}

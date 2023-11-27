using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Caching;
using System.Text;
using System.Net.Http;
using LetsGoBikingLibrary2.Models;

using System.Threading.Tasks;
using System.ServiceModel;

namespace LetsGoBikingProxy.Services

    //PROBLEME LE PROXY NE DOIT PAS CONNAITRE LES MODELS, SINON CA FAIT UN MODEL TRES GROS ET COMPLEXE
    // LE PROXY DOIT JUSTE RENVOYER CE QUE JCDECAUX RENVOIE LORSQUON FAIT LA REQUETE HTTP


{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]
    public class StationProxyService : IStationProxyService
    {
        private readonly HttpClient _httpClient = new HttpClient();
        private readonly string _jcDecauxApiKey = "0484963fbd484dfeb5bf83031ef743273bf62fbc";
        private MemoryCache _cache = new MemoryCache("StationProxyServiceCache");

        public async Task<List<Station>> GetAllStationsAsync(string contractName)
        {
            string cacheKey = $"Stations_{contractName}";
            if (!_cache.Contains(cacheKey))
            {
                var response = await _httpClient.GetAsync($"https://api.jcdecaux.com/vls/v1/stations?contract={contractName}&apiKey={_jcDecauxApiKey}");
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var stations = JsonConvert.DeserializeObject<List<Station>>(content);

                // Ajoute les données en cache avec une expiration
                CacheItemPolicy policy = new CacheItemPolicy { AbsoluteExpiration = DateTimeOffset.Now.AddMinutes(5) };
                _cache.Add(new CacheItem(cacheKey, stations), policy);

                return stations;
            }
            else
            {
                return (List<Station>)_cache.Get(cacheKey);
            }
        }

        public async Task<List<Contract>> GetAllContractsAsync()
        {
            string cacheKey = "Contracts";
            if (!_cache.Contains(cacheKey))
            {
                var response = await _httpClient.GetAsync($"https://api.jcdecaux.com/vls/v1/contracts?apiKey={_jcDecauxApiKey}");
                response.EnsureSuccessStatusCode();
                var content = await response.Content.ReadAsStringAsync();
                var contracts = JsonConvert.DeserializeObject<List<Contract>>(content);

                // Ajoute les données en cache avec une expiration
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


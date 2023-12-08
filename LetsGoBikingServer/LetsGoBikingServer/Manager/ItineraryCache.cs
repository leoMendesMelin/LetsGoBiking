using System;
using System.Runtime.Caching;
using LetsGoBikingLibrary2.Models;

namespace LetsGoBikingServer.Manager
{
    public class ItineraryCache
    {
        private static ItineraryCache _instance;
        private MemoryCache _cache = new MemoryCache("ItineraryCache");

        private ItineraryCache() { }

        public static ItineraryCache GetInstance()
        {
            if (_instance == null)
            {
                _instance = new ItineraryCache();
            }
            return _instance;
        }

        public bool TryGetItinerary(string startAddress, string endAddress, out CompleteRoute route)
        {
            string key = GenerateKey(startAddress, endAddress);
            route = _cache.Get(key) as CompleteRoute;
            // Si la route n'est pas dans le cache, route vaut null
            //Affihcer dans la console si la route est dans le cache
            if(route != null)
            {
                Console.WriteLine("Cache hit pour l'itinéraire " + key);
            }
            else
            {
                Console.WriteLine("Cache miss pour l'itinéraire " + key);
            }
            return route != null;
        }

        public void AddItinerary(string startAddress, string endAddress, CompleteRoute route)
        {
            string key = GenerateKey(startAddress, endAddress);
            CacheItemPolicy policy = new CacheItemPolicy { AbsoluteExpiration = DateTimeOffset.Now.AddMinutes(100) }; // Exemple de durée d'expiration
            _cache.Add(new CacheItem(key, route), policy);
        }

        private string GenerateKey(string startAddress, string endAddress)
        {
            return $"{startAddress}-{endAddress}".ToLower();
        }
    }
}

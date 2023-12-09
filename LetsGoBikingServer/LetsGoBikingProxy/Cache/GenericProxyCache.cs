using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Caching;
using System.Text;
using System.Threading.Tasks;

namespace LetsGoBikingProxy.Cache
{
    public class GenericProxyCache<T>
    {
        private MemoryCache _cache;
        private DateTimeOffset _defaultExpiration;

        public GenericProxyCache()
        {
            _cache = new MemoryCache(typeof(T).Name);
            _defaultExpiration = ObjectCache.InfiniteAbsoluteExpiration;
        }

        public void SetDefaultExpiration(DateTimeOffset expiration)
        {
            _defaultExpiration = expiration;
        }

        public async Task<T> Get(string cacheItemName, Func<Task<T>> dataRetrievalFunction)
        {
            if (_cache.Contains(cacheItemName))
            {
                Console.WriteLine("Cache hit pour " + cacheItemName);
                return (T)_cache.Get(cacheItemName);
            }
            else
            {
                Console.WriteLine("Cache miss pour " + cacheItemName);
                T data = await dataRetrievalFunction();
                _cache.Set(cacheItemName, data, _defaultExpiration);
                return data;
            }
        }

        public async Task<T> Get(string cacheItemName, double dtSeconds, Func<Task<T>> dataRetrievalFunction)
        {
            return await Get(cacheItemName, DateTimeOffset.Now.AddSeconds(dtSeconds), dataRetrievalFunction);
        }

        public async Task<T> Get(string cacheItemName, DateTimeOffset expiration, Func<Task<T>> dataRetrievalFunction)
        {
            if (_cache.Contains(cacheItemName))
            {
                Console.WriteLine("Cache hit pour " + cacheItemName);
                return (T)_cache.Get(cacheItemName);
            }
            else
            {
                Console.WriteLine("Cache miss pour " + cacheItemName);
                T data = await dataRetrievalFunction();
                _cache.Set(cacheItemName, data, expiration);
                return data;
            }
        }
    }
}

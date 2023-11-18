using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingServer.Utils
{
    public static class JsonUtils
    {
        public static T DeserializeJson<T>(string json)
        {
            return JsonSerializer.Deserialize<T>(json);
        }
    }
}
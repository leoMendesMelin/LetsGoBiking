using LetsGoBikingServer.Models;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingServer.Utils
{
    public class PositionConverter : JsonConverter
    {
        public override bool CanConvert(Type objectType)
        {
            return (objectType == typeof(Position));
        }

        public override object ReadJson(JsonReader reader, Type objectType, object existingValue, JsonSerializer serializer)
        {
            JObject jo = JObject.Load(reader);
            var latToken = jo["latitude"] ?? jo["lat"];
            var lonToken = jo["longitude"] ?? jo["lng"] ?? jo["lon"];

            return new Position
            {
                Lat = latToken != null ? latToken.ToObject<double>() : 0,
                Lon = lonToken != null ? lonToken.ToObject<double>() : 0
            };
        }
        public override void WriteJson(JsonWriter writer, object value, JsonSerializer serializer)
        {
            // Implémentez si nécessaire pour la sérialisation
        }
    }
}
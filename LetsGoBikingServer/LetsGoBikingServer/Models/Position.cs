using LetsGoBikingServer.Utils;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingServer.Models
{
    [JsonConverter(typeof(PositionConverter))]
    public class Position
    {
        public double Lat { get; set; }
        public double Lon { get; set; }
    }
}
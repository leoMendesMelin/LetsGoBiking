 // Assurez-vous d'avoir cette référence
using Newtonsoft.Json;
using LetsGoBikingLibrary2.Utils;

namespace LetsGoBikingLibrary2.Models
{
    [JsonConverter(typeof(PositionConverter))]
    public class Position
    {
        public double Lat { get; set; }
        public double Lon { get; set; }
    }
}
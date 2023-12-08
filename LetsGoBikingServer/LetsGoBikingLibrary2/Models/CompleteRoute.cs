using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingLibrary2.Models
{
    public class CompleteRoute
    {
        public CompleteRoute()
        {
            Id = Guid.NewGuid().ToString(); // Génère un ID unique
        }
        public string Id { get; private set; }
        public string queueId { get; set; }

        public Position startPosition { get; set; }
        public Position endPosition { get; set; }
        public Station StartStation { get; set; }
        public Station EndStation { get; set; }

        public RouteResponse WalkToStartStation { get; set; }
        public RouteResponse BikeRoute { get; set; }
        public RouteResponse WalkToEnd { get; set; }
    }
}
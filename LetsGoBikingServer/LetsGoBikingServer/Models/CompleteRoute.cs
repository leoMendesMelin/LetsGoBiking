using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingServer.Models
{
    public class CompleteRoute
    {
        public RouteResponse WalkToStartStation { get; set; }
        public RouteResponse BikeRoute { get; set; }
        public RouteResponse WalkToEnd { get; set; }
    }
}
using LetsGoBikingClientC_.RoutingServiceReference;
using LetsGoBikingClientC_.StationServiceReference;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace LetsGoBikingClientC_
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            StationServiceClient client = new StationServiceClient();
            var stations = await client.GetAllStationsAsync("Lyon");

            RoutingServiceReference.RoutingServiceClient routingClient = new RoutingServiceReference.RoutingServiceClient();

            var route = await routingClient.GetPositionAsync("Rue des néfliers, montreuil, france");

            Console.WriteLine($"Route: {route.Lat}");
            Console.WriteLine($"Route: {route.Lon}");

            string contractName = client.GetClosestContract(route.Lat, route.Lon);
            Console.WriteLine($"Contract: {contractName}");
            //Affiche les stations
           
           

            }
    }
}

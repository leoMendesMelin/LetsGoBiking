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

            var adressStart = await routingClient.GetPositionAsync("Rue des néfliers, montreuil, france");
            var adresseEnd = await routingClient.GetPositionAsync("67 Av. Pablo Picasso, 92000 Nanterre");

            string start = "Rue des néfliers, montreuil, france";
            string end = "67 Av. Pablo Picasso, 92000 Nanterre";


            
            await routingClient.GetCompleteRouteAsync(start,end);






        }
    }
}

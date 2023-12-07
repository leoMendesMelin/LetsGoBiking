using System;
using LetsGoBikingClientC_.RoutingServiceReference;
using System.Threading.Tasks;
using System.Linq;

namespace LetsGoBikingClientC_
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            RoutingServiceClient routingClient = new RoutingServiceClient();
            string start = "8-22 Rue de Rocroy, 94100 Saint-Maur-des-Fossés";
            string end = "8-22 Rue de Rocroy, 94100 Saint-Maur-des-Fossés";

            string endNotNecessaryBike = "Smile World Créteil, Centre Commercial Créteil Soleil, Av. de la France libre, 94000 Créteil";

            string casse = "Espace Sante, 13004 Marseille";


            CompleteRoute completeRoute = await routingClient.GetCompleteRouteAsync(start, end);
    
            if(completeRoute.BikeRoute != null)
            {
                //walk to start station
                Console.WriteLine("WalkRoute to start station");
                DisplayRouteSegment("WalkRoute", completeRoute.WalkToStartStation);
                Console.WriteLine("BikeRoute");
                DisplayRouteSegment("BikeRoute", completeRoute.BikeRoute);
                Console.WriteLine("WalkRoute to end station");
                DisplayRouteSegment("WalkRoute", completeRoute.WalkToEnd);
            }
            //elif
            if(completeRoute.BikeRoute == null && completeRoute.WalkToStartStation !=null)
            {
                Console.WriteLine("WalkRoute to end adress because not necessary to get a bike");
                DisplayRouteSegment("Total WalkRoute", completeRoute.WalkToStartStation);
            }
        }


        static void DisplayRouteSegment(string description, RouteResponse routeSegment)
        {
            if (routeSegment != null && routeSegment.Features != null && routeSegment.Features.Any())
            {
                double totalDistance = routeSegment.Features.Sum(f => f.Properties.Summary.Distance);
                double totalTime = routeSegment.Features.Sum(f => f.Properties.Summary.Duration);

                Console.WriteLine($"{description}:");
                Console.WriteLine($"- Distance: {totalDistance} m");
                Console.WriteLine($"- Durée: {TimeSpan.FromSeconds(totalTime)}");
            }
            else
            {
                Console.WriteLine($"{description} n'est pas disponible.");
            }
        }
    }
}

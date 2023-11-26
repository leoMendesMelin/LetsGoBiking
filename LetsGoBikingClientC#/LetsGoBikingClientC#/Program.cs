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
            string start = "Avenue de la France Libre, Créteil";
            string end = "Avenue du Maréchal de Lattre de Tassigny, Créteil";

            CompleteRoute route = await routingClient.GetCompleteRouteAsync(start, end);

            if (route != null)
            {
                // Afficher les stations de départ et d'arrivée
                string startStationName = GetStationName(route.WalkToStartStation);
                string endStationName = GetStationName(route.WalkToEnd, true);

                Console.WriteLine($"Station de départ: {startStationName}");
                Console.WriteLine($"Station d'arrivée: {endStationName}");

                // Afficher les détails de l'itinéraire
                DisplayRouteSegment("Marche jusqu'à la station de départ", route.WalkToStartStation);
                DisplayRouteSegment("Trajet en vélo", route.BikeRoute);
                DisplayRouteSegment("Marche de la station d'arrivée à la destination", route.WalkToEnd);
            }
        }

        static string GetStationName(RouteResponse routeSegment, bool isEndStation = false)
        {
            if (routeSegment != null && routeSegment.Features != null && routeSegment.Features.Any())
            {
                var steps = routeSegment.Features.First().Properties.Segments.SelectMany(s => s.Steps).ToList();
                if (steps.Any())
                {
                    // La station de départ est généralement la première étape et la station d'arrivée la dernière
                    var step = isEndStation ? steps.Last() : steps.First();
                    return step.Name;
                }
            }
            return "Information non disponible";
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

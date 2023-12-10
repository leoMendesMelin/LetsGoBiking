using System;
using System.ServiceModel;
using LetsGoBikingServer.Services;

namespace LetsGoBikingServer
{
    class Program
    {
        static void Main(string[] args)
        {
            // Obtenez l'instance singleton du RoutingService.
            RoutingService routingService = RoutingService.GetInstance();

            // Créez le ServiceHost en utilisant l'instance singleton.
            ServiceHost hostRouting = new ServiceHost(routingService, new Uri("http://localhost:8001/RoutingService"));
            hostRouting.AddServiceEndpoint(typeof(IRoutingService), new BasicHttpBinding(), "");

            try
            {
                // Démarrer le service.
                hostRouting.Open();
                Console.WriteLine("RoutingService est prêt à l'adresse : http://localhost:8001/RoutingService");

                // Initialisez le service après le démarrage.
                routingService.InitializeAsync().GetAwaiter().GetResult();
                Console.WriteLine("Le cache est initialisé.");

                Console.WriteLine("Appuyez sur <Entrée> pour arrêter les services.");
                Console.ReadLine();
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Une exception est survenue: {ex.Message}");
            }
            finally
            {
                // Arrêter le service.
                if (hostRouting.State == CommunicationState.Opened)
                {
                    hostRouting.Close();
                    Console.WriteLine("RoutingService est arrêté.");
                }
            }
        }
    }
}

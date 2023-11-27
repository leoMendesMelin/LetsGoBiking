using LetsGoBikingServer.Services;
using System;
using System.ServiceModel;
using LetsGoBikingProxy.Services; // Ajoutez le namespace approprié pour StationProxyService

namespace LetsGoBikingServer
{
    class Program
    {
        static void Main(string[] args)
        {
            // Héberger le RoutingService
            ServiceHost hostRouting = CreateServiceHost(typeof(RoutingService), "http://localhost:8001/RoutingService");

            // Ajoutez la ligne suivante pour héberger le StationProxyService
            //ServiceHost hostStationProxy = CreateServiceHost(typeof(StationProxyService), "http://localhost:8000/StationProxyService");

            try
            {
                StartService(hostRouting, "RoutingService");
               // StartService(hostStationProxy, "StationProxyService"); // Démarrer le StationProxyService

                //lancer les appels pour récupérer les données de JC DECAUX comme ça le cache est rempli

                Console.WriteLine("Appuyez sur <Entrée> pour arrêter les services.");
                Console.ReadLine();
            }
            finally
            {
                StopService(hostRouting, "RoutingService");
               // StopService(hostStationProxy, "StationProxyService"); // Arrêter le StationProxyService
            }
        }

        static ServiceHost CreateServiceHost(Type serviceType, string address)
        {
            Uri serviceAddress = new Uri(address);
            ServiceHost host = new ServiceHost(serviceType, serviceAddress);
            host.AddServiceEndpoint(serviceType.GetInterfaces()[0], new BasicHttpBinding(), "");
            return host;
        }

        static void StartService(ServiceHost host, string serviceName)
        {
            host.Open();
            Console.WriteLine($"{serviceName} est prêt à l'adresse : {host.BaseAddresses[0]}");
        }

        static void StopService(ServiceHost host, string serviceName)
        {
            if (host != null)
            {
                host.Close();
                Console.WriteLine($"{serviceName} a été arrêté.");
            }
        }
    }
}

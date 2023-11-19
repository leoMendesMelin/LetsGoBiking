using LetsGoBikingServer.Services;
using System.ServiceModel;
using System;

namespace LetsGoBikingServer
{
    class Program
    {
        static void Main(string[] args)
        {
            ServiceHost hostStation = CreateServiceHost(typeof(StationService), "http://localhost:8000/StationService");
            ServiceHost hostRouting = CreateServiceHost(typeof(RoutingService), "http://localhost:8001/RoutingService");

            try
            {
                StartService(hostStation, "StationService");
                StartService(hostRouting, "RoutingService");

                Console.WriteLine("Appuyez sur <Entrée> pour arrêter les services.");
                Console.ReadLine();
            }
            finally
            {
                StopService(hostStation, "StationService");
                StopService(hostRouting, "RoutingService");
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

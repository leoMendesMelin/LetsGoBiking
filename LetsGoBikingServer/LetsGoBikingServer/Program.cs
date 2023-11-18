using LetsGoBikingServer.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel.Description;
using System.ServiceModel;
using System.Web;

namespace LetsGoBikingServer
{
    class Program
    {
        static void Main(string[] args)
        {
            Uri baseAddress = new Uri("http://localhost:8000/StationService");
            using (ServiceHost host = new ServiceHost(typeof(StationService), baseAddress))
            {
                //juste un print
                Console.WriteLine("Le service est prêt à l'adresse : {0}", baseAddress);
                // Ajoutez ici les endpoints, les comportements, les bindings, etc.
                host.AddServiceEndpoint(typeof(IStationService), new BasicHttpBinding(), "StationService");

                // Enable metadata publishing.
                //ServiceMetadataBehavior smb = new ServiceMetadataBehavior { HttpGetEnabled = true };
                //host.Description.Behaviors.Add(smb);

                // Ouvrez le ServiceHost pour commencer à écouter les messages entrants.
                host.Open();

                Console.WriteLine("Le service est prêt à l'adresse : {0}", baseAddress);
                Console.WriteLine("Appuyez sur <Entrée> pour arrêter le service.");
                Console.ReadLine();

                // Fermez le ServiceHost pour arrêter le service.
                host.Close();
            }
        }
    }
}
using LetsGoBikingProxy.Services;
using System;
using System.ServiceModel;
using System.ServiceModel.Description;

namespace LetsGoBikingProxy
{
    class Program
    {
        static void Main(string[] args)
        {
            // Définir l'adresse de base du service
            Uri baseAddress = new Uri("http://localhost:8000/StationProxyService");

            // Créer le service hôte avec l'adresse de base spécifiée
            using (ServiceHost host = new ServiceHost(typeof(StationProxyService), baseAddress))
            {
                // Ajouter un point de terminaison pour le service
                host.AddServiceEndpoint(typeof(IStationProxyService), new BasicHttpBinding(), "");

                // Trouver ou ajouter le ServiceMetadataBehavior pour activer le WSDL
                ServiceMetadataBehavior smb = host.Description.Behaviors.Find<ServiceMetadataBehavior>();
                if (smb == null)
                {
                    smb = new ServiceMetadataBehavior { HttpGetEnabled = true };
                    host.Description.Behaviors.Add(smb);
                }
                else
                {
                    smb.HttpGetEnabled = true;
                }

                // Démarrer le service
                host.Open();

                // Afficher l'URL du service
                Console.WriteLine($"StationProxyService est démarré à l'adresse suivante : {baseAddress}");
                Console.WriteLine("Appuyez sur <Entrée> pour arrêter le service.");

                // Attendre que l'utilisateur appuie sur Entrée pour arrêter le service
                Console.ReadLine();

                // Fermer le service hôte
                host.Close();
            }
        }
    }
}

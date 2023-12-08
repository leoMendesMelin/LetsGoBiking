using System;
using LetsGoBikingClientC_.RoutingServiceReference;
using System.Threading.Tasks;
using System.Linq;
using System.Runtime.Remoting.Messaging;
using System.ServiceModel.Channels;
using Apache.NMS.ActiveMQ;
using Apache.NMS;
using ISession = Apache.NMS.ISession;
using IMessage = Apache.NMS.IMessage;

namespace LetsGoBikingClientC_
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            RoutingServiceClient routingClient = new RoutingServiceClient();
            string endLouis = "31 Mnt de la Chapelle, 04120 Peyroules";
            string start = "8 Rue de Rocroy, 94100 Saint-Maur-des-Fossés";
            string endAPiedSansBike = "8-22 Rue de Rocroy, 94100 Saint-Maur-des-Fossés";
            string endEnVelo = "40-54 Rue des Sarrazins, 94000 Créteil";
            string endNotNecessaryBike = "4 Av. du Président Wilson, 94340 Joinville-le-Pont";

            string casse = "Espace Sante, 13004 Marseille";


            CompleteRoute completeRoute= await routingClient.GetCompleteRouteAsync(start, endLouis);


            //afficher la route en utilisant displayRouteSegment
            DisplayRouteSegment("Route à pied", completeRoute.WalkToStartStation) ;
            DisplayRouteSegment("Route en vélo", completeRoute.BikeRoute);
            DisplayRouteSegment("Route à pied", completeRoute.WalkToEnd);

            string queueId = completeRoute.queueId; // Supposons que vous receviez l'ID de la file d'attente dans completeRoute
            ReceiveStepsFromQueue(queueId);



        }

        static void ReceiveStepsFromQueue(string queueId)
        {
            string brokerUri = "tcp://localhost:61616"; // URI du broker ActiveMQ
            IConnectionFactory factory = new ConnectionFactory(brokerUri);

            using (IConnection connection = factory.CreateConnection())
            using (ISession session = connection.CreateSession())
            {
                IDestination destination = session.GetQueue(queueId);
                using (IMessageConsumer consumer = session.CreateConsumer(destination))
                {
                    connection.Start();
                    Console.WriteLine("En attente de messages de la file d'attente...");

                    // Boucle de réception des messages
                    IMessage message;
                    while ((message = consumer.Receive(TimeSpan.FromSeconds(10))) != null)
                    {
                        ITextMessage textMessage = message as ITextMessage;
                        if (textMessage != null)
                        {
                            // Traiter le message reçu
                            Console.WriteLine("Message reçu: " + textMessage.Text);
                            // Vous pouvez ici décomposer le message et l'afficher ou le traiter comme nécessaire
                        }
                        else
                        {
                            Console.WriteLine("Message non textuel reçu.");
                        }
                    }
                    Console.WriteLine("Aucun message reçu après 10 secondes, fin de l'écoute.");
                }
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

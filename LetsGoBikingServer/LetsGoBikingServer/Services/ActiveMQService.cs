using System;
using Apache.NMS;
using Apache.NMS.ActiveMQ;
using Apache.NMS.ActiveMQ.Commands;
using LetsGoBikingLibrary2.Models;
using Microsoft.SqlServer.Server;

namespace LetsGoBikingServer.Services
{
    public class ActiveMQService
    {
        private readonly string _brokerUri;
        private IConnectionFactory _connectionFactory;
        private IConnection _connection;
        private ISession _session;

        public ActiveMQService(string brokerUri)
        {
            _brokerUri = brokerUri;
            InitializeActiveMQ();
        }

        private void InitializeActiveMQ()
        {
            _connectionFactory = new ConnectionFactory(_brokerUri);
            _connection = _connectionFactory.CreateConnection();
            _connection.Start();
            _session = _connection.CreateSession();
        }

        public string CreateQueueSendSteps(CompleteRoute route)
        {
            string queueId = Guid.NewGuid().ToString();
            IDestination destination = _session.GetQueue(queueId);
            route.queueId = queueId;
            Console.WriteLine("Création de la queue " + queueId);
            Console.WriteLine("Envoi des messages dans la queue " + queueId);

            if (route.WalkToStartStation != null)
            {
                CreateTheMessageToSend(route.WalkToStartStation,route.queueId, "Trajet à pied : ");
            }
            if (route.BikeRoute != null)
            {
                CreateTheMessageToSend(route.BikeRoute, route.queueId,"Trajet à vélo jusqu'à la station d'arrivée : ");
            }
            if (route.WalkToEnd != null)
            {
                CreateTheMessageToSend(route.WalkToEnd, route.queueId,"Trajet à pied jusqu'à l'adresse d'arrivée : ");
            }
            Console.WriteLine("Fin de l'envoi des messages dans la queue " + queueId);
            // Pas besoin de créer un producer ici, car il sera créé lors de l'envoi du message.
            return queueId;
        }

        public void CreateTheMessageToSend(RouteResponse route, string queueId,string typeRoute)
        {
            foreach (var feature in route.Features)
            {
                foreach (var segment in feature.Properties.Segments)
                {
                    foreach (var step in segment.Steps)
                    {
                        string messageStep = typeRoute + step.Instruction + " " + step.Distance + " " + step.Duration + " " +
                                             step.StartLatitude + " " + step.StartLongitude + " " +
                                             step.EndLatitude + " " + step.EndLongitude + " " + step.Type;

                        SendMessage(queueId, messageStep);
                    }
                }
            }
        }

        public void SendMessage(string queueId, string messageText)
        {
            IDestination destination = _session.GetQueue(queueId);
            using (IMessageProducer producer = _session.CreateProducer(destination))
            {
                ITextMessage message = _session.CreateTextMessage(messageText);
                producer.Send(message);
            }
        }

        public void Dispose()
        {
            _session?.Close();
            _connection?.Close();
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using Apache.NMS;
using Apache.NMS.ActiveMQ;
using Apache.NMS.ActiveMQ.Commands;
using System;
using System.Web;

namespace LetsGoBikingServer.Services
{
    public class ActiveMQService
    {
        private readonly string _brokerUri;
        private readonly string _queueName;
        private IConnectionFactory _connectionFactory;
        private IConnection _connection;
        private ISession _session;
        private IMessageProducer _producer;

        public ActiveMQService(string brokerUri, string queueName)
        {
            _brokerUri = brokerUri;
            _queueName = queueName;
            InitializeActiveMQ();
        }

        private void InitializeActiveMQ()
        {
            _connectionFactory = new ConnectionFactory(_brokerUri);
            _connection = _connectionFactory.CreateConnection();
            _connection.Start();
            _session = _connection.CreateSession();
            IDestination destination = _session.GetQueue(_queueName);
            _producer = _session.CreateProducer(destination);
        }

        public void SendMessage(string messageText)
        {
            ITextMessage message = _session.CreateTextMessage(messageText);
            _producer.Send(message);
        }

        public void Dispose()
        {
            _session?.Close();
            _connection?.Close();
        }
    }
}
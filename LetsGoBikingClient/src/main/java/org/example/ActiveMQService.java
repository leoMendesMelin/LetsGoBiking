package org.example;

import com.soap.ws.client.generated.RouteResponse;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

class ActiveMQService {
    private String brokerUrl;
    private String queueName;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    public ActiveMQService(String brokerUrl, String queueName) {
        this.brokerUrl = brokerUrl;
        this.queueName = queueName;
    }

    public void start() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        consumer = session.createConsumer(destination);
        System.out.println("Connexion à ActiveMQ démarrée.");
    }

    public RouteResponse receiveMessages() throws JMSException {
        // Logique pour recevoir les messages et les convertir en données d'itinéraire
        // ...
        return null; // Stub pour l'exemple
    }

    public void stop() throws JMSException {
        session.close();
        connection.close();
        System.out.println("Connexion à ActiveMQ arrêtée.");
    }
}
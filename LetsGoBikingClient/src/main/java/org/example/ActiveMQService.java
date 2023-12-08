package org.example;

import com.soap.ws.client.generated.RouteResponse;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQService {
    private String brokerUrl;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    public ActiveMQService(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public void start(String queueId) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueId);
        consumer = session.createConsumer(destination);
        System.out.println("Connexion à ActiveMQ démarrée sur la queue: " + queueId);
    }

    public void receiveMessages() throws JMSException {
        try {
            while (true) {
                Message message = consumer.receive(1000); // Attendre le message pendant 1000 ms (1 seconde)
                if (message == null) {
                    break; // Sortir de la boucle si aucun message n'est reçu
                }

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Message reçu: " + text);
                } else {
                    System.out.println("Message reçu n'est pas un message texte.");
                }
            }
        } catch (JMSException e) {
            System.err.println("Exception lors de la réception du message: " + e.getMessage());
            throw e;
        }
    }

    public void stop() throws JMSException {
        session.close();
        connection.close();
        System.out.println("Connexion à ActiveMQ arrêtée.");
    }
}

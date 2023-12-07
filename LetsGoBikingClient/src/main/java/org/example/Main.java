package org.example;
import java.util.concurrent.ExecutionException;
import com.soap.ws.client.generated.*;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.concurrent.ExecutionException;
import com.soap.ws.client.generated.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, JMSException {
        System.out.println("Hello World!");

        // Créer le client de service
        RoutingService routingService = new RoutingService();
        IRoutingService portRouting = routingService.getBasicHttpBindingIRoutingService();

        String start = "Avenue de la France Libre, Créteil";
        String end = "Avenue du Maréchal de Lattre de Tassigny, Créteil";

        // Appeler le service et obtenir la route complète de manière asynchrone
        CompleteRoute route = portRouting.getCompleteRoute(start, end);

        if (route != null) {
            // Afficher les détails de l'itinéraire
            displayRouteSegment("Marche jusqu'à la station de départ", route.getWalkToStartStation().getValue());
            displayRouteSegment("Trajet en vélo", route.getBikeRoute().getValue());
            displayRouteSegment("Marche de la station d'arrivée à la destination", route.getWalkToEnd().getValue());
        }

        ActiveMQService activeMQService = new ActiveMQService("tcp://localhost:61616", "LetGoBikingQueue");
        //MapService mapService = new MapService();

        // Exécution des services
        activeMQService.start();
        RouteResponse routeData = activeMQService.receiveMessages();
        activeMQService.stop();

        if (routeData != null) {
            //mapService.displayRoute();
        }
    }

    static void displayRouteSegment(String description, RouteResponse routeSegment) {
        if (routeSegment != null && routeSegment.getFeatures() != null && !routeSegment.getFeatures().isNil()) {
            double totalDistance = routeSegment.getFeatures().getValue().getFeature().stream()
                    .mapToDouble(f -> f.getProperties().getValue().getSummary().getValue().getDistance())
                    .sum();
            double totalTime = routeSegment.getFeatures().getValue().getFeature().stream()
                    .mapToDouble(f -> f.getProperties().getValue().getSummary().getValue().getDuration())
                    .sum();

            System.out.println(description + ":");
            System.out.println("- Distance: " + totalDistance + " m");
            System.out.println("- Durée: " + formatDuration(totalTime));
        } else {
            System.out.println(description + " n'est pas disponible.");
        }
    }

    static String formatDuration(double totalSeconds) {
        long seconds = (long) totalSeconds % 60;
        long minutes = (long) (totalSeconds / 60) % 60;
        long hours = (long) (totalSeconds / (60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    static void receiveMessages(MessageConsumer consumer) throws JMSException {
        while (true) {
            Message message = consumer.receive(1000);  // Attendre le message pendant 1 seconde

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Message reçu: " + text);
            } else if (message == null) {
                System.out.println("Aucun nouveau message. Fin de l'attente.");
                break;
            }
        }
    }
}
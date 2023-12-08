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

        String start = "8 Rue de Rocroy, 94100 Saint-Maur-des-Fossés";
        String endAPiedSansBike = "8-22 Rue de Rocroy, 94100 Saint-Maur-des-Fossés";
        String endEnVelo = "40-54 Rue des Sarrazins, 94000 Créteil";
        String endNotNecessaryBike = "4 Av. du Président Wilson, 94340 Joinville-le-Pont";

        String casse = "Espace Sante, 13004 Marseille";

        // Appeler le service et obtenir la route complète de manière asynchrone


        CompleteRoute route = portRouting.getCompleteRoute(start, endEnVelo);

        // Utiliser l'ID de la queue de la réponse pour démarrer ActiveMQService
        try{
            ActiveMQService activeMQService = new ActiveMQService("tcp://localhost:61616");
            activeMQService.start(route.getQueueId().getValue());  // Utilisation de l'ID de la queue spécifique
            activeMQService.receiveMessages();
            activeMQService.stop();
        }
        catch (Exception e){
            System.out.println("ActiceMQService n'est pas dispoible");
            showStepsFromCompleteRoute(route);
        }



    }

    public static void showStepsFromCompleteRoute(CompleteRoute route) {
        if(route.getBikeRoute().getValue()!=null){
            showStepsFromRouteResponse(route.getWalkToStartStation().getValue(),"Trajet à pied : ");
            showStepsFromRouteResponse(route.getBikeRoute().getValue(),"Trajet en vélo : ");
            showStepsFromRouteResponse(route.getWalkToEnd().getValue(),"Trajet à pied : ");
        }else{
            showStepsFromRouteResponse(route.getWalkToStartStation().getValue(),"Trajet à pied : ");
        }
    }

    public static void showStepsFromRouteResponse(RouteResponse value, String typeRoute) {
        for (Feature feature : value.getFeatures().getValue().getFeature()) {
            for (Segment segment : feature.getProperties().getValue().getSegments().getValue().getSegment()) {
                for (Step step : segment.getSteps().getValue().getStep()) {
                    String messageStep = typeRoute + " " + step.getInstruction().getValue() + " " +
                            step.getDistance() + " " + step.getDuration() + " " +
                            step.getStartLatitude() + " " + step.getStartLongitude() + " " +
                            step.getEndLatitude() + " " + step.getEndLongitude() + " " +
                            step.getType();
                    System.out.println(messageStep);

                }
            }
        }
    }


}
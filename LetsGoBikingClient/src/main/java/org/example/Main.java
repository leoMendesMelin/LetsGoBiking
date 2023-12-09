package org.example;

import com.soap.ws.client.generated.*; // Remplacez ceci par votre package généré

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.jms.JMSException;
import javax.swing.JFrame;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

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


        // Attendre la réponse

        Map map = Map.getInstance();


        // Utiliser l'ID de la queue de la réponse pour démarrer ActiveMQService
        try{

            ActiveMQService activeMQService = new ActiveMQService("tcp://localhost:61616");
            activeMQService.start(route.getQueueId().getValue());  // Utilisation de l'ID de la queue spécifique
            map.launchMap(route);
            activeMQService.receiveMessages(map);
            activeMQService.stop();

        }
        catch (Exception e){
            System.out.println("ActiceMQService n'est pas dispoible");
            List<String> steps = showStepsFromCompleteRoute(route);
            map.launchMap(route);
            map.setSteps(steps);

        }


    }




    public static List<String> showStepsFromCompleteRoute(CompleteRoute route) {
        List<String> listInstructions = new ArrayList<>();
        if (route.getBikeRoute().getValue() != null) {
            listInstructions.addAll(showStepsFromRouteResponse(route.getWalkToStartStation().getValue(), "Trajet à pied : "));
            listInstructions.addAll(showStepsFromRouteResponse(route.getBikeRoute().getValue(), "Trajet en vélo : "));
            listInstructions.addAll(showStepsFromRouteResponse(route.getWalkToEnd().getValue(), "Trajet à pied : "));
        } else {
            listInstructions.addAll(showStepsFromRouteResponse(route.getWalkToStartStation().getValue(), "Trajet à pied : "));
        }
        return listInstructions;
    }

    public static List<String> showStepsFromRouteResponse(RouteResponse value, String typeRoute) {
        List<String> result = new ArrayList<>();
        for (Feature feature : value.getFeatures().getValue().getFeature()) {
            for (Segment segment : feature.getProperties().getValue().getSegments().getValue().getSegment()) {
                for (Step step : segment.getSteps().getValue().getStep()) {
                    String messageStep = typeRoute + " " + step.getInstruction().getValue() + " " +
                            step.getDistance() + " " + step.getDuration() + " " +
                            step.getStartLatitude() + " " + step.getStartLongitude() + " " +
                            step.getEndLatitude() + " " + step.getEndLongitude() + " " +
                            step.getType();
                    result.add(messageStep);

                }
            }
        }
        return result; // Retourne les instructions pour une portion de l'itinéraire
    }

}
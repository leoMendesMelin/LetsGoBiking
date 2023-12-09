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


        Map map = Map.getInstance();
        map.launchEmptyMap();

    }






}
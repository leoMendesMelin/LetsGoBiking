package org.example;

import com.soap.ws.client.generated.CompleteRoute;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Map {

    public static void launchMap(CompleteRoute route) {
        JXMapViewer mapViewer = new JXMapViewer();

        // Display the viewer in a JFrame
        JFrame frame = new JFrame("JXMapviewer Example");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        List<GeoPosition> geoPositions = getGeoPositionFromRouteResponse(route);

        // Create waypoints from the geo-positions
        Set<Waypoint> waypoints = new HashSet<>();
        for (GeoPosition position : geoPositions) {
            waypoints.add(new DefaultWaypoint(position));
        }



        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);


        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        if(geoPositions.size()>2){
            RoutePainter walkingRoutePainter = new RoutePainter(Arrays.asList(geoPositions.get(0), geoPositions.get(1)), Color.BLUE);
            RoutePainter bikeRoutePainter = new RoutePainter(Arrays.asList(geoPositions.get(1), geoPositions.get(2)), Color.RED);
            RoutePainter walkingRouteEndPainter = new RoutePainter(Arrays.asList(geoPositions.get(2), geoPositions.get(3)), Color.BLUE);
            painters.add(walkingRoutePainter);
            painters.add(bikeRoutePainter);
            painters.add(walkingRouteEndPainter);
        }else{
            RoutePainter walkingRoutePainter = new RoutePainter(Arrays.asList(geoPositions.get(0), geoPositions.get(1)), Color.BLUE);
            painters.add(walkingRoutePainter);
        }
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(painters);
        mapViewer.setOverlayPainter(painter);

        // Set the focus
        mapViewer.zoomToBestFit(new HashSet<>(geoPositions), 0.7);
    }

    public static List<GeoPosition> getGeoPositionFromRouteResponse(CompleteRoute route) {
        List<GeoPosition> listGeoPosition = new ArrayList<>();
        if(route.getBikeRoute().getValue() != null){
            listGeoPosition.add(new GeoPosition(
                    route.getStartPosition().getValue().getLat(),
                    route.getStartPosition().getValue().getLon()));
            listGeoPosition.add(new GeoPosition(
                    route.getStartStation().getValue().getPosition().getValue().getLat(),
                    route.getStartStation().getValue().getPosition().getValue().getLon()));
            listGeoPosition.add(new GeoPosition(
                    route.getEndStation().getValue().getPosition().getValue().getLat(),
                    route.getEndStation().getValue().getPosition().getValue().getLon()));
            listGeoPosition.add(new GeoPosition(
                    route.getEndPosition().getValue().getLat(),
                    route.getEndPosition().getValue().getLon()));
        } else {
            listGeoPosition.add(new GeoPosition(
                    route.getStartPosition().getValue().getLat(),
                    route.getStartPosition().getValue().getLon()));
            listGeoPosition.add(new GeoPosition(
                    route.getEndPosition().getValue().getLat(),
                    route.getEndPosition().getValue().getLon()));
        }
        return listGeoPosition;
    }
}

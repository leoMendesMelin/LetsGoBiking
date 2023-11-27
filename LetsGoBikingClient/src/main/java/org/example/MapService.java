package org.example;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.*;
import org.jxmapviewer.painter.RoutePainter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MapService {

    public MapService() {
    }

    public void displayRoute(List<GeoPosition> track) {
        // Create a JXMapViewer
        JXMapViewer mapViewer = new JXMapViewer();

        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Set the focus
        mapViewer.setZoom(7);
        mapViewer.setAddressLocation(track.get(0));

        // Create a track from the geo-positions
        RoutePainter routePainter = new RoutePainter(track);

        // Create a compound painter that uses both the route painter
        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(routePainter);

        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<SwingWaypoint> waypointPainter = new SwingWaypointPainter<>();
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(painters);
        mapViewer.setOverlayPainter(painter);

        // Display the viewer in a JFrame
        JFrame frame = new JFrame("JXMapviewer2 Example");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class SwingWaypoint extends DefaultWaypoint {
    private final JButton button;
    private final String text;

    public SwingWaypoint(String text, GeoPosition coord) {
        super(coord);
        this.text = text;
        button = new JButton(text);
        button.addActionListener(e -> System.out.println("Waypoint clicked: " + text));
    }

    public JButton getButton() {
        return button;
    }
}

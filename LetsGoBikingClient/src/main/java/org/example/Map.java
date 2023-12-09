package org.example;

import com.soap.ws.client.generated.*;
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

    private JLabel instructionLabel; // Label to display the current step instruction
    private JButton nextStepButton; // Button to go to the next step
    private int currentStepIndex = 0; // Current step index
    private List<String> steps; // List of all the steps in the route
    private JXMapViewer mapViewer; // Map viewer

    private List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
    List<GeoPosition> geoPositionsMain = new ArrayList<>();
    List<GeoPosition> geoPositionsSteps = new ArrayList<>();
    private CompleteRoute route;

    //singleton
    private static Map instance = null;
    private Map() {
    }
    public static Map getInstance() {
        if(instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public void launchMap(CompleteRoute route) {
        this.route = route;
        // Initialisation des listes et configuration de l'interface utilisateur
        this.steps = new ArrayList<>();
        initUI(route);

        // Configuration du visualisateur de carte et du cadre de l'application
        this.mapViewer = new JXMapViewer();
        JFrame frame = new JFrame("JXMapviewer Example");
        frame.setLayout(new BorderLayout());

        // Configuration du panneau d'instructions
        JPanel instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.add(instructionLabel, BorderLayout.CENTER);
        instructionsPanel.add(nextStepButton, BorderLayout.PAGE_END);
        frame.add(instructionsPanel, BorderLayout.EAST);

        // Configuration de la fabrique de tuiles
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Obtention des positions géographiques pour les waypoints et les étapes
        this.geoPositionsMain = getGeoPositionFromRouteResponse(route);
        this.setGeoPositionsSteps(route);

        // Initialisation des peintres de route et des waypoints
        initializeRoutePainters();

        // Ajout du visualisateur de carte au cadre et configuration pour l'affichage
        frame.add(this.mapViewer, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Ajustement du zoom pour inclure tous les waypoints
        this.mapViewer.zoomToBestFit(new HashSet<>(geoPositionsSteps), 0.7);
    }

    public void initializeRoutePainters() {
        this.painters.clear();

        // Si un segment à vélo est présent, on crée trois tracés distincts
        if (route.getBikeRoute().getValue() != null) {
            addRouteToPainters(route.getWalkToStartStation().getValue(), Color.BLACK);
            addRouteToPainters(route.getBikeRoute().getValue(), Color.BLUE);
            addRouteToPainters(route.getWalkToEnd().getValue(), Color.BLACK);
        } else {
            // Sinon, un seul tracé pour l'itinéraire à pied
            addRouteToPainters(route.getWalkToStartStation().getValue(), Color.BLUE);
        }

        // Ajoutez les waypoints pour les positions principales
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        Set<Waypoint> waypoints = new HashSet<>();
        for (GeoPosition geoPosition : this.geoPositionsMain) {
            waypoints.add(new DefaultWaypoint(geoPosition));
        }
        waypointPainter.setWaypoints(waypoints);
        this.painters.add(waypointPainter);

        // Mettez à jour le CompoundPainter avec les nouveaux peintres
        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(this.painters);
        this.mapViewer.setOverlayPainter(painter);
    }



    private void addRouteToPainters(RouteResponse routeResponse, Color color) {
        GeoPosition previousPosition = null; // Pour garder la dernière position de la fin d'une étape
        boolean isFirstStep = true; // Pour savoir si nous sommes à la première étape

        // Créez une liste temporaire pour stocker les GeoPositions
        List<GeoPosition> tempGeoPositions = new ArrayList<>();

        for (Feature feature : routeResponse.getFeatures().getValue().getFeature()) {
            for (Segment segment : feature.getProperties().getValue().getSegments().getValue().getSegment()) {
                for (Step step : segment.getSteps().getValue().getStep()) {
                    // Pour la première étape, ajoutez la position de départ
                    if (isFirstStep) {
                        previousPosition = new GeoPosition(step.getStartLatitude(), step.getStartLongitude());
                        tempGeoPositions.add(previousPosition);
                        isFirstStep = false;
                    }
                    // Pour toutes les étapes, ajoutez la position de fin
                    GeoPosition currentPosition = new GeoPosition(step.getEndLatitude(), step.getEndLongitude());
                    tempGeoPositions.add(currentPosition);

                    // Créez un tracé entre la position précédente et la position actuelle
                    if (previousPosition != null) {
                        RoutePainter routePainter = new RoutePainter(Arrays.asList(previousPosition, currentPosition), color);
                        this.painters.add(routePainter);
                    }
                    previousPosition = currentPosition; // Mettre à jour la position précédente pour le prochain segment
                }
            }
        }

        // Ajoutez la liste temporaire à la liste principale des positions
        this.geoPositionsSteps.addAll(tempGeoPositions);
    }






























    private void initUI(CompleteRoute route) {
        // Taille fixe pour le label des instructions
        final int labelWidth = 300; // Largeur fixe pour le label
        final int labelHeight = 100; // Hauteur fixe pour le label

        // Utilisez des balises HTML pour permettre les retours à la ligne automatiques
        this.instructionLabel = new JLabel("<html><body style='width: " + labelWidth + "px'>" + "Instruction for the current step" + "</body></html>");
        this.instructionLabel.setPreferredSize(new Dimension(labelWidth, labelHeight)); // Définissez la taille préférée du label
        this.instructionLabel.setVerticalAlignment(SwingConstants.TOP); // Alignez le texte en haut

        // Créer le bouton pour aller à l'étape suivante
        this.nextStepButton = new JButton("Next Step");
        this.nextStepButton.addActionListener(e -> showNextStep());

        // Ajoutez d'autres composants si nécessaire...
    }

    public void showNextStep() {
        this.currentStepIndex++;
        if (currentStepIndex < steps.size()) {
            String currentStep = steps.get(currentStepIndex);
            instructionLabel.setText("<html><body style='width: " + 200 + "px'>" + currentStep + "</body></html>"); // Update the label with the new step instruction
            updateMapForNextStep(); // Mettre à jour la carte à l'étape suivante
        } else {
            instructionLabel.setText("<html><body style='width: " + 200 + "px'>" + "You have arrived at your destination." + "</body></html>");
            nextStepButton.setEnabled(false); // Disable the button if it's the last step
        }
    }

    public void updateMapForNextStep() {
        System.out.println("Nombre de steps : "+this.geoPositionsSteps.size()/2);
        System.out.println("Nombre d'instructions : "+this.steps.size());

        this.painters.remove(this.painters.get(0));


        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        Set<Waypoint> waypoints = new HashSet<>();
        for (GeoPosition geoPosition : geoPositionsMain) {
            waypoints.add(new DefaultWaypoint(geoPosition));
        }
        waypointPainter.setWaypoints(waypoints);
        this.painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(this.painters);
        mapViewer.setOverlayPainter(painter);
        mapViewer.repaint();
    }



    public void setSteps(List<String> steps) {
        this.steps = steps;
    }


    public void setGeoPositionsSteps(CompleteRoute route){
        if (route.getBikeRoute().getValue() != null) {
            transformRouteResponseToGeoPositions(route.getWalkToStartStation().getValue());
            transformRouteResponseToGeoPositions(route.getBikeRoute().getValue());
            transformRouteResponseToGeoPositions(route.getWalkToEnd().getValue());
        } else {
            transformRouteResponseToGeoPositions(route.getWalkToStartStation().getValue());
        }
    }

    public void transformRouteResponseToGeoPositions(RouteResponse routeResponse) {
        // Initialiser une liste temporaire pour stocker les GeoPositions
        List<GeoPosition> tempGeoPositions = new ArrayList<>();

        // Nous commençons par ajouter la position de départ de la première étape
        boolean isFirstStep = true;

        for (Feature feature : routeResponse.getFeatures().getValue().getFeature()) {
            for (Segment segment : feature.getProperties().getValue().getSegments().getValue().getSegment()) {
                for (Step step : segment.getSteps().getValue().getStep()) {
                    // Pour la première étape, ajoutez la position de départ
                    if (isFirstStep) {
                        tempGeoPositions.add(new GeoPosition(step.getStartLatitude(), step.getStartLongitude()));
                        isFirstStep = false;
                    }
                    // Pour toutes les étapes, ajoutez la position de fin
                    tempGeoPositions.add(new GeoPosition(step.getEndLatitude(), step.getEndLongitude()));
                }
            }
        }

        this.geoPositionsSteps.addAll(tempGeoPositions);
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

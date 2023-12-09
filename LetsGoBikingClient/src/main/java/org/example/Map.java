package org.example;

import com.soap.ws.client.generated.*;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Map {

    private JLabel instructionLabel; // Label to display the current step instruction
    private JButton nextStepButton; // Button to go to the next step
    private JTextField startAddressField;
    private JTextField endAddressField;
    private JButton validateButton;
    private RoutingService routingService = new RoutingService();
    private IRoutingService portRouting = routingService.getBasicHttpBindingIRoutingService();
    private int currentStepIndex = 0; // Current step index
    private List<String> steps; // List of all the steps in the route
    private JXMapViewer mapViewer; // Map viewer
    private List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
    List<GeoPosition> geoPositionsMain = new ArrayList<>();
    List<GeoPosition> geoPositionsSteps = new ArrayList<>();
    private CompleteRoute route;
    private JFrame frame;
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

        // Assurez-vous que les composants sont réinitialisés et prêts à afficher les nouvelles données
        if (this.mapViewer != null) {
            this.mapViewer.setOverlayPainter(null);
            this.painters.clear();
        } else {
            this.mapViewer = new JXMapViewer();
            TileFactoryInfo info = new OSMTileFactoryInfo();
            DefaultTileFactory tileFactory = new DefaultTileFactory(info);
            mapViewer.setTileFactory(tileFactory);
            this.frame.add(this.mapViewer, BorderLayout.CENTER);
        }


        // Configuration des données de la carte
        this.geoPositionsMain = getGeoPositionFromRouteResponse(route);
        this.setGeoPositionsSteps(route);
        initializeRoutePainters();
        this.mapViewer.zoomToBestFit(new HashSet<>(geoPositionsSteps), 0.7);


        if(!this.geoPositionsSteps.isEmpty()){
            this.nextStepButton.setEnabled(true);
        }
        // Redessinez la carte avec les nouveaux peintres et la nouvelle route
        this.frame.revalidate();
        this.frame.repaint();
    }

    public void launchEmptyMap() {
        if (this.frame == null) {
            initUI();
            this.frame.setVisible(true);
        }
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



    private void initUI() {
        // Cette méthode ne devrait être appelée qu'une seule fois pour configurer l'interface utilisateur
        if (this.frame == null) {
            this.frame = new JFrame("JXMapviewer Example");
            this.frame.setLayout(new BorderLayout());
            this.frame.setSize(800, 600);
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Configuration des composants d'interface utilisateur pour les adresses
            startAddressField = new JTextField(20);
            endAddressField = new JTextField(20);

            validateButton = new JButton("Valider");
            validateButton.addActionListener(e -> fetchRouteAndUpdateMap());



            // Panneau pour les composants d'entrée
            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("Départ:"));
            inputPanel.add(startAddressField);
            inputPanel.add(new JLabel("Arrivée:"));
            inputPanel.add(endAddressField);
            inputPanel.add(validateButton);

            // Ajouter le panneau d'entrée au cadre
            this.frame.add(inputPanel, BorderLayout.SOUTH);

            // Configuration du visualisateur de carte sans afficher de route
           // mapViewer = new JXMapViewer();
            mapViewer = new JXMapKit().getMainMap();

            TileFactoryInfo info = new OSMTileFactoryInfo();
            DefaultTileFactory tileFactory = new DefaultTileFactory(info);
            mapViewer.setTileFactory(tileFactory);
            this.frame.add(mapViewer, BorderLayout.CENTER);

            // Créer le bouton pour aller à l'étape suivante
            nextStepButton = new JButton("Next Step");
            nextStepButton.addActionListener(e -> showNextStep());
            nextStepButton.setEnabled(false); // Désactivez le bouton jusqu'à ce qu'une route soit chargée

            // Utilisez des balises HTML pour permettre les retours à la ligne automatiques
            instructionLabel = new JLabel("<html><body style='width: " + 300 + "px'>" + "Instruction for the current step" + "</body></html>");
            instructionLabel.setPreferredSize(new Dimension(300, 100)); // Définissez la taille préférée du label
            instructionLabel.setVerticalAlignment(SwingConstants.TOP); // Alignez le texte en haut

            // Panneau pour les instructions et le bouton next step
            JPanel instructionsPanel = new JPanel(new BorderLayout());
            instructionsPanel.add(instructionLabel, BorderLayout.CENTER);
            instructionsPanel.add(nextStepButton, BorderLayout.PAGE_END);

            //mouse
            //mouse
            mapViewer.addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int zoom = mapViewer.getZoom();
                    int wheelRotation = e.getWheelRotation();
                    int newZoom = zoom - wheelRotation;
                    mapViewer.setZoom(newZoom);
                    mapViewer.repaint();
                }
            });

            // Ajouter la possibilité de se déplacer sur la carte en faisant glisser la souris
            MouseInputListener mia = new PanMouseInputListener(mapViewer);
            mapViewer.addMouseListener(mia);
            mapViewer.addMouseMotionListener(mia);

            // Ajouter les composants au cadre
            this.frame.add(instructionsPanel, BorderLayout.EAST);

            // Ne pas oublier de redessiner le cadre après avoir ajouté tous les composants
            this.frame.revalidate();
            this.frame.repaint();
            this.frame.setVisible(true);
        }
    }

    private void fetchRouteAndUpdateMap() {

        String start = startAddressField.getText();
        String end = endAddressField.getText();
        System.out.println("dans fetch");
        // Vérifiez si les adresses ne sont pas vides
        if (!start.isEmpty() && !end.isEmpty()) {
            try {
                System.out.println("j'appelle le serveur !");

                // Appeler le service et obtenir la route complète de manière asynchrone
                CompleteRoute newRoute = portRouting.getCompleteRoute(start, end);

                try{
                    ActiveMQService activeMQService = new ActiveMQService("tcp://localhost:61616");
                    activeMQService.start(newRoute.getQueueId().getValue());  // Utilisation de l'ID de la queue spécifique
                    activeMQService.receiveMessages(this);
                    activeMQService.stop();
                    this.launchMap(newRoute);


                }
                catch (Exception e){
                    System.out.println("ActiceMQService n'est pas dispoible");
                    List<String> steps = showStepsFromCompleteRoute(newRoute);
                    this.setSteps(steps);
                    this.launchMap(newRoute);

                }

                // Mettre à jour la carte avec la nouvelle route

                launchMap(newRoute);

                System.out.println("okk j'ai mis à jour bg");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                    "Une erreur est survenue lors de la récupération de l'itinéraire : " + ex.getMessage(),
                    "Erreur d'Itinéraire",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();

            }
        }
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
        System.out.println("nombre de steps : " + steps.size());
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
                    //System.out.println(messageStep);

                }
            }
        }
        return result; // Retourne les instructions pour une portion de l'itinéraire
    }
}

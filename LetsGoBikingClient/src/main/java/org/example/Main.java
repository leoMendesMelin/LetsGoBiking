package org.example;

import com.soap.ws.client.generated.*;
public class Main {
    public static void main(String[] args) {
        // Create a service object
        StationService service = new StationService();
        RoutingService serviceRouting = new RoutingService();

        // Get the port to the service
        //IStationService port = service.getBasicHttpBindingIStationService();
        IRoutingService portRouting = serviceRouting.getBasicHttpBindingIRoutingService();

        //ArrayOfStation response = port.getAllStations("Lyon");
        Position position = portRouting.getPosition("2400 route des dolines");


//        for (Station station : response.getStation()) {
//            String name = station.getName() != null ? station.getName().getValue() : "Unknown";
//            String address = station.getAddress() != null ? station.getAddress().getValue() : "No address provided";
//            System.out.println("Station ID: " + station.getNumber()
//                    + ", Name: " + name
//                    + ", Address: " + address);
//        }
        System.out.println("Latitude: " + position.getLat());
        System.out.println("Longitude: " + position.getLng());
    }
}
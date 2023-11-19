package org.example;

import com.soap.ws.client.generated.*;
public class Main {
    public static void main(String[] args) {
        // Create a service object
        StationService service = new StationService();
        // Get the port to the service
        IStationService port = service.getBasicHttpBindingIStationService();

        // Assuming 'getAllStations' is a method provided by the service
        // and it does not take any parameters
        ArrayOfStation response = port.getAllStations("Lyon");

        // Process the response as needed
        // For example, print out station details
        for (Station station : response.getStation()) {
            String name = station.getName() != null ? station.getName().getValue() : "Unknown";
            String address = station.getAddress() != null ? station.getAddress().getValue() : "No address provided";
            System.out.println("Station ID: " + station.getNumber()
                    + ", Name: " + name
                    + ", Address: " + address);
        }
    }
}
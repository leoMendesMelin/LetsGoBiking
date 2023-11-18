package org.example;

import com.soap.ws.client.generated.*;

public class Main {
    public static void main(String[] args) {
        // Instanciez le service
        StationService service = new StationService();
        // Obtenez le port pour le service
        IStationService port = service.getBasicHttpBindingIStationService();

        // Créez une requête pour obtenir toutes les stations
        GetAllStations request = new GetAllStations();

        // Appelez la méthode et récupérez la réponse
        ArrayOfStation response = port.getAllStations("Lyon");

        // Traitez la réponse comme vous le souhaitez
        // Par exemple, affichez les stations
        response.getStation().forEach(station ->
                System.out.println("Station: " + station.getName() + " Address: " + station.getAddress())
        );
    }
}

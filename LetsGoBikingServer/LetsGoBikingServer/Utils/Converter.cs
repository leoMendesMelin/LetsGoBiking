using LetsGoBikingLibrary2.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingServer.Utils
{
    public class Converter
    {
        public  List<List<double>> ExtractCoordinatesFromJson(string jsonResponse)
        {
            // Pseudo-code pour extraire juste le champ "coordinates" du JSON
            // Vous devez remplacer ceci par le code réel qui extrait les coordonnées
            dynamic json = JsonConvert.DeserializeObject(jsonResponse);
            var coordinates = new List<List<double>>();
            foreach (var coord in json.features[0].geometry.coordinates)
            {
                coordinates.Add(new List<double> { (double)coord[0], (double)coord[1] });
            }
            return coordinates;
        }

        public void PopulateStepCoordinates(RouteResponse routeResponse, List<List<double>> coordinates)
        {
            foreach (var feature in routeResponse.Features)
            {
                foreach (var segment in feature.Properties.Segments)
                {
                    foreach (var step in segment.Steps)
                    {
                        if (step.way_points != null && step.way_points.Count == 2)
                        {
                            int startIndex = step.way_points[0];
                            int endIndex = step.way_points[1];

                            if (startIndex >= 0 && startIndex < coordinates.Count)
                            {
                                step.StartLongitude = coordinates[startIndex][0];
                                step.StartLatitude = coordinates[startIndex][1];
                            }
                            else
                            {
                                Console.WriteLine($"Index de waypoint de début {startIndex} est hors limites.");
                            }

                            if (endIndex >= 0 && endIndex < coordinates.Count)
                            {
                                step.EndLongitude = coordinates[endIndex][0];
                                step.EndLatitude = coordinates[endIndex][1];
                            }
                            else
                            {
                                Console.WriteLine($"Index de waypoint de fin {endIndex} est hors limites.");
                            }
                        }
                        else
                        {
                            Console.WriteLine("Step sans deux waypoints valides.");
                        }
                    }
                }
            }
        }


    }
}
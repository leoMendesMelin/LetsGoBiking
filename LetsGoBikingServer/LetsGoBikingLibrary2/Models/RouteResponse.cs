using System;
using System.Collections.Generic;

namespace LetsGoBikingLibrary2.Models
{
    public class RouteResponse
    {
        public List<Feature> Features { get; set; }
    }

    public class Feature
    {
        public Properties Properties { get; set; }
        // Si nécessaire, ajoutez d'autres propriétés qui apparaissent dans "Feature" du JSON
    }

    public class Properties
    {
        public List<Segment> Segments { get; set; }
        public Summary Summary { get; set; }
        // Assurez-vous que toutes les autres propriétés dans "Properties" du JSON sont également mappées ici
    }

    public class Segment
    {
        public double Distance { get; set; }
        public double Duration { get; set; }
        public List<Step> Steps { get; set; }
        // Ajoutez d'autres propriétés de "Segment" si nécessaire
    }

    public class Step
    {
        public double Distance { get; set; }
        public double Duration { get; set; }
        public string Instruction { get; set; }
        public string Name { get; set; }
        public List<int> way_points { get; set; }
        public int Type { get; set; } // Ajouté si "type" est important pour votre logique

        // Les champs Latitude et Longitude ne sont pas dans le JSON mais sont ajoutés séparément dans votre logique
        public double StartLatitude { get; set; }
        public double StartLongitude { get; set; }
        public double EndLatitude { get; set; }
        public double EndLongitude { get; set; }
    }

    public class Summary
    {
        public double Distance { get; set; }
        public double Duration { get; set; }
        // Ajoutez ici toutes les autres propriétés de "Summary" du JSON
    }

    // Si d'autres classes ou propriétés sont présentes dans le JSON, assurez-vous de les ajouter également
}

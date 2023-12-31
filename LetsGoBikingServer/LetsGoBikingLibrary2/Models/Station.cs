﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingLibrary2.Models
{
    public class Station
    {
        public int number { get; set; }
        public string contractName { get; set; }
        public string name { get; set; }
        public string address { get; set; }
        public Position position { get; set; }

        // Nouveaux attributs ajoutés
        public int available_bike_stands { get; set; }
        public int available_bikes { get; set; }
        public string status { get; set; }
    }
}
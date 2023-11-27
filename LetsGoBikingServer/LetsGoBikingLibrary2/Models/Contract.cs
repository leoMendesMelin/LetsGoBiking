using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingLibrary2.Models
{
    public class Contract
    {
        public string name { get; set; }
        public string commercial_name { get; set; }
        public List<string> cities { get; set; }
        public string country_code { get; set; }

        // Ajoutez d'autres propriétés si nécessaire, selon les données de l'API
    }
}
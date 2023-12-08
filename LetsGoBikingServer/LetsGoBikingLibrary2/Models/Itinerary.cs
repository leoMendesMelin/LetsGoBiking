using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace LetsGoBikingLibrary2.Models
{
    [DataContract] // Indique que cette classe est un contrat de données
    public class Itinerary
    {
        [DataMember] // Indique que cette propriété doit être sérialisée
        public string Id { get; private set; }

        [DataMember] // Indique que cette propriété doit être sérialisée
        public CompleteRoute Route { get; private set; }

        public Itinerary(CompleteRoute route)
        {
            Id = Guid.NewGuid().ToString(); // Génère un ID unique
            Route = route;
        }
    }

}

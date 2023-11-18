using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.ServiceModel;
using System.Threading.Tasks;
using System.Web;
using System.Web.Routing;

namespace LetsGoBikingServer.Services
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]

    public class RoutingService : IRoutingService
    {
        private readonly HttpClient _client = new HttpClient();
        public RoutingService() { }
        public Task<string> GetRouteAsync(string from, string to)
        {
            //var response = await _client.GetAsync($"https://api.jcdecaux.com/vls/v1/stations?contract={contractName}&apiKey={_apiKey}");
            //response.EnsureSuccessStatusCode();
            //var content = await response.Content.ReadAsStringAsync();
            // Parsez la réponse pour construire et renvoyer un objet Route
            //return new Route(); // Remplacez ceci par le code de désérialisation et de construction de l'objet Route
            throw new NotImplementedException();
        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using static System.Collections.Specialized.BitVector32;
using LetsGoBikingLibrary2.Models;

namespace LetsGoBikingServer.Services
{
    [ServiceContract]
    internal interface IRoutingService
    {
        [OperationContract]
        //Transform address to position
        Task<Position> GetPositionAsync(string address);

        [OperationContract]
        Task<string> GetClosestContractAsync(double userLatitude, double userLongitude);

        [OperationContract]
        Task<Station> GetClosestStationsAsync(double userLatitude, double userLongitude, int numberOfStations, string closestContract);

        [OperationContract]
        Task<RouteResponse> GetRouteAsync(double startLat, double startLon, double endLat, double endLon, string typeRoute);

        [OperationContract]
        Task<CompleteRoute> GetCompleteRouteAsync(string startAddress, string endAddress);

        [OperationContract]
        Task<bool> CheckStationAvailable(string contract, Station station, string typeStation);




    }
}


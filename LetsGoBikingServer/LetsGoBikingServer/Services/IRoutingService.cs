using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using static System.Collections.Specialized.BitVector32;
using LetsGoBikingServer.Models;

namespace LetsGoBikingServer.Services
{
    [ServiceContract]
    internal interface IRoutingService
    {
        [OperationContract]
        //Transform address to position
        Task<Position> GetPositionAsync(string address);

        [OperationContract]
        Task<double> CalculateDistanceAsync(Position start, Position arrival);


    }
}

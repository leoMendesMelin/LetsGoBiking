﻿using System;
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
    public interface IStationService
    {
        [OperationContract]
        //Task<string> GetAllStationsAsync(string contractName);
        Task<List<Station>> GetAllStationsAsync(string contractName);

        [OperationContract]
        //find closest station from a given location
        Task<string> GetClosestContractAsync(double userLatitude, double userLongitude);
        [OperationContract]
        Task<List<Station>> GetClosestStationsAsync(double userLatitude, double userLongitude, int numberOfStations);
    }



}

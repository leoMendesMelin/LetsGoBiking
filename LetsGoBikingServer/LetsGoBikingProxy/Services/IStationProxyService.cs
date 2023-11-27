using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Caching;
using System.ServiceModel;
using LetsGoBikingLibrary2.Models;

namespace LetsGoBikingProxy.Services
{
    [ServiceContract]
    public interface IStationProxyService
    {
        [OperationContract]
        Task<List<Station>> GetAllStationsAsync(string contractName);

        [OperationContract]
        Task<List<Contract>> GetAllContractsAsync();
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingServer.Exceptions
{
    public class NoStationFoundException : Exception
    {
        public NoStationFoundException(string message) : base(message)
        {
        }
    }
}
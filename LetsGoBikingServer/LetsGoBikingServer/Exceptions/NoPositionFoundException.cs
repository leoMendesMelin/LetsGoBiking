using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingServer.Exceptions
{
    public class NoPositionFoundException : Exception
    {
        public NoPositionFoundException(string message) : base(message)
        {
        }
    }
}
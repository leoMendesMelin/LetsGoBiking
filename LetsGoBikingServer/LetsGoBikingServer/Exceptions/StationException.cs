﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace LetsGoBikingServer.Exceptions
{
    public class StationException : Exception
    {
        public StationException(string message) : base(message)
        {
        }
    }
}
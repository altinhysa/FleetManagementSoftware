package com.spines.fleetmanagementsoftware.exceptions;

public class VehicleNotAvailableException extends Exception{
    public VehicleNotAvailableException() {
    }

    public VehicleNotAvailableException(String message) {
        super(message);
    }
}

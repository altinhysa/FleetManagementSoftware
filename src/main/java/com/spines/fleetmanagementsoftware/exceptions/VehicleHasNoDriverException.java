package com.spines.fleetmanagementsoftware.exceptions;

public class VehicleHasNoDriverException extends Exception{
    public VehicleHasNoDriverException() {
    }

    public VehicleHasNoDriverException(String message) {
        super(message);
    }
}

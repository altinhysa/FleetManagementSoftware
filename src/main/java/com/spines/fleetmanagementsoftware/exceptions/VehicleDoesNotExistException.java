package com.spines.fleetmanagementsoftware.exceptions;

public class VehicleDoesNotExistException extends Exception{
    public VehicleDoesNotExistException() {
    }

    public VehicleDoesNotExistException(String message) {
        super(message);
    }
}

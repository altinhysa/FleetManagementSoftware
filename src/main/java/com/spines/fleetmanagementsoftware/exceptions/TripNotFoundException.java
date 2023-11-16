package com.spines.fleetmanagementsoftware.exceptions;

public class TripNotFoundException extends Exception{
    public TripNotFoundException() {
    }

    public TripNotFoundException(String message) {
        super(message);
    }
}

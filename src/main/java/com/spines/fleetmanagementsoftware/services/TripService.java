package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.exceptions.VehicleHasNoDriverException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotAvailableException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.models.Trip;

import java.util.List;

public interface TripService {

    Trip createTrip(Trip trip,long vehicleId) throws VehicleHasNoDriverException, VehicleNotAvailableException, VehicleNotFoundException;

    Trip updateTrip(Trip trip);

    Trip findById(long id);

    List<Trip> getAll();

    Trip deleteById(long id);

}

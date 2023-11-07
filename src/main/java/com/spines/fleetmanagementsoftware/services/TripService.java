package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.models.Trip;

import java.util.List;

public interface TripService {

    Trip createTrip(Trip trip,long vehicleId);

    Trip updateTrip(Trip trip);

    Trip findById(long id);

    List<Trip> getAll();

    Trip deleteById(long id);

}

package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.exceptions.TripNotFoundException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleHasNoDriverException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotAvailableException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.models.Trip;
import com.spines.fleetmanagementsoftware.models.VehicleStatus;
import com.spines.fleetmanagementsoftware.models.dtos.TripDto;

import java.util.List;
import java.util.Map;

public interface TripService {

    TripDto createTrip(TripDto trip, long vehicleId) throws VehicleHasNoDriverException, VehicleNotAvailableException, VehicleNotFoundException;

    TripDto updateTrip(TripDto trip);

    TripDto findById(long id) throws TripNotFoundException;

    List<TripDto> getAll();

    TripDto deleteById(long id) throws TripNotFoundException, VehicleNotFoundException;

    Double getSpendings();

}

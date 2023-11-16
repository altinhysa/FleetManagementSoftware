package com.spines.fleetmanagementsoftware.controllers;

import com.spines.fleetmanagementsoftware.exceptions.TripNotFoundException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleHasNoDriverException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotAvailableException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.models.Trip;
import com.spines.fleetmanagementsoftware.services.TripService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public Trip addTrip(@RequestBody Trip trip, @RequestParam long vehicleId) throws VehicleNotAvailableException, VehicleHasNoDriverException, VehicleNotFoundException {// edhe ktu e kam tu Ex
        return tripService.createTrip(trip,vehicleId);
    }

    @GetMapping
    public List<Trip> getAll(){
        return tripService.getAll();
    }

    @GetMapping("/{id}")
    public Trip getById(@PathVariable long id) throws TripNotFoundException {
        return tripService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Trip removeById(@PathVariable long id) throws VehicleNotFoundException, TripNotFoundException {
        return tripService.deleteById(id);
    }
}

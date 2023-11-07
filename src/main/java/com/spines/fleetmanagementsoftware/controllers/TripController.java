package com.spines.fleetmanagementsoftware.controllers;

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
    public Trip addTrip(@RequestBody Trip trip, @RequestParam long vehicleId){
        return tripService.createTrip(trip,vehicleId);
    }

    @GetMapping
    public List<Trip> getAll(){
        return tripService.getAll();
    }
}

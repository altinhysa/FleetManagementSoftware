package com.spines.fleetmanagementsoftware.controllers;

import com.spines.fleetmanagementsoftware.exceptions.TripNotFoundException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleHasNoDriverException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotAvailableException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.models.dtos.TripDto;
import com.spines.fleetmanagementsoftware.services.interfaces.TripService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/trips")
@CrossOrigin
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public TripDto addTrip(@RequestBody TripDto trip, @RequestParam long vehicleId) throws VehicleNotAvailableException, VehicleHasNoDriverException, VehicleNotFoundException {// edhe ktu e kam tu Ex
        return tripService.createTrip(trip,vehicleId);
    }

    @GetMapping
    public List<TripDto> getAll(){
        return tripService.getAll();
    }

    @GetMapping("/{id}")
    public TripDto getById(@PathVariable long id) throws TripNotFoundException {
        return tripService.findById(id);
    }

    @DeleteMapping("/{id}")
    public TripDto removeById(@PathVariable long id) throws VehicleNotFoundException, TripNotFoundException {
        return tripService.deleteById(id);
    }

    @GetMapping("/spendings")
    public Double getSpendings(){
        return tripService.getSpendings();
    }
}

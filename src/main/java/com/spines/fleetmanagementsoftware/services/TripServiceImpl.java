package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.exceptions.TripNotFoundException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleHasNoDriverException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotAvailableException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.models.*;
import com.spines.fleetmanagementsoftware.repositories.TripRepository;
import com.spines.fleetmanagementsoftware.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;

    public TripServiceImpl(TripRepository tripRepository, VehicleRepository vehicleRepository) {
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Trip createTrip(Trip trip, long vehicleId) throws VehicleHasNoDriverException, VehicleNotAvailableException, VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(VehicleNotFoundException::new);

        if (vehicle.getDriver() == null) {
            throw new VehicleHasNoDriverException("Vehicle has no driver");      //a mujm edhe ktu me bo exeption
        }

        if (!vehicle.getStatus().equals(VehicleStatus.AVAILABLE)) {
            throw new VehicleNotAvailableException("Vehicle is not available");
        }

        vehicle.setStatus(VehicleStatus.EN_ROUTE);
        vehicle.setOdometer(vehicle.getOdometer() + trip.getDistance());
        vehicle.setServiceDistance(vehicle.getServiceDistance() + trip.getDistance());
        if (vehicle.getServiceDistance() > 15_000) {
            Maintenance maintenance = new Maintenance();
            maintenance.setVehicle(vehicle);
            maintenance.setDate(Date.valueOf(LocalDate.now().plusDays(1)));
            maintenance.setMaintenanceType(MaintenanceType.REGULAR_SERVICE);
            maintenance.setComment("Vehicle is due Regular Service. Please Update Maintenaince Info");
            vehicle.getMaintenances().add(maintenance);
            vehicle.setServiceDistance(0);
        }
        double fuelConsumption = trip.getFuelUsed() / trip.getDistance();
        trip.setFuelConsumption(fuelConsumption);
        trip.setVehicle(vehicle);

        double tripCoast = trip.getFuelUsed() * trip.getFuelPrice();
        trip.setTripCost(tripCoast);
        trip.setVehicle(vehicle); // sdi a e kom mire



        return tripRepository.save(trip);
    }

    @Override
    public Trip updateTrip(Trip trip) {
        return null;
    }

    @Override
    public Trip findById(long id) throws TripNotFoundException {
        return tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
    }

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip deleteById(long id) throws TripNotFoundException, VehicleNotFoundException {
        Trip trip = tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
        Vehicle vehicle = vehicleRepository.findById(trip.getVehicle().getId()).orElseThrow(VehicleNotFoundException::new);
        vehicle.setOdometer(vehicle.getOdometer() - trip.getDistance());
        tripRepository.delete(trip);
        return trip;
    }}

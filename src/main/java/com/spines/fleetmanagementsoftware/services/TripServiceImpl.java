package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.models.*;
import com.spines.fleetmanagementsoftware.repositories.TripRepository;
import com.spines.fleetmanagementsoftware.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
public class TripServiceImpl implements TripService{

    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;

    public TripServiceImpl(TripRepository tripRepository, VehicleRepository vehicleRepository) {
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Trip createTrip(Trip trip,long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(RuntimeException::new);

        if (vehicle.getDriver() == null){
            throw new RuntimeException("Vehicle has no driver");
        }

        if (!vehicle.getStatus().equals(VehicleStatus.AVAILABLE)){
            throw new RuntimeException("Vehicle is not available");
        }

        vehicle.setStatus(VehicleStatus.EN_ROUTE);
        vehicle.setOdometer(vehicle.getOdometer() + trip.getDistance());
        vehicle.setServiceDistance(vehicle.getServiceDistance() + trip.getDistance());
        if (vehicle.getServiceDistance() > 15_000){
            Maintenance maintenance = new Maintenance();
            maintenance.setVehicle(vehicle);
            maintenance.setDate(Date.valueOf(LocalDate.now().plusDays(1)));
            maintenance.setMaintenanceType(MaintenanceType.REGULAR_SERVICE);
            maintenance.setComment("Vehicle is due Regular Service. Please Update Maintenaince Info");
            vehicle.getMaintenances().add(maintenance);
            vehicle.setServiceDistance(0);
        }
        trip.setVehicle(vehicle);

        return tripRepository.save(trip);
    }

    @Override
    public Trip updateTrip(Trip trip) {
        return null;
    }

    @Override
    public Trip findById(long id) {
        return null;
    }

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip deleteById(long id) {
        return null;
    }
}

package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.exceptions.TripNotFoundException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleHasNoDriverException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotAvailableException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.mappers.TripMapper;
import com.spines.fleetmanagementsoftware.models.*;
import com.spines.fleetmanagementsoftware.models.dtos.TripDto;
import com.spines.fleetmanagementsoftware.repositories.TripRepository;
import com.spines.fleetmanagementsoftware.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
public class TripServiceImpl implements TripService {

    private final ModelMapper modelMapper;

    private final TripMapper tripMapper;
    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;

    public TripServiceImpl(ModelMapper modelMapper, TripMapper tripMapper, TripRepository tripRepository, VehicleRepository vehicleRepository) {
        this.modelMapper = modelMapper;
        this.tripMapper = tripMapper;
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public TripDto createTrip(TripDto dto, long vehicleId) throws VehicleHasNoDriverException, VehicleNotAvailableException, VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(VehicleNotFoundException::new);

        if (vehicle.getDriver() == null) {
            throw new VehicleHasNoDriverException("Vehicle has no driver");      //a mujm edhe ktu me bo exeption
        }

        if (!vehicle.getStatus().equals(VehicleStatus.AVAILABLE)) {
            throw new VehicleNotAvailableException("Vehicle is not available");
        }

        Trip trip = tripMapper.toEntity(dto);

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

        double tripCoast = trip.getFuelUsed() * trip.getFuelPrice();
        trip.setTripCost(tripCoast);

        trip.setVehicle(vehicle);

        return tripMapper.toDto(trip) ;
    }

    @Override
    public TripDto updateTrip(TripDto trip) {
        return null;
    }

    @Override
    public TripDto findById(long id) {
        return tripMapper.toDto(tripRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Trip not found with id  "  + id)));
    }

    @Override
    public List<TripDto> getAll() {
        return tripRepository.findAll().stream().map(tripMapper::toDto).toList();
    }

    @Override
    public TripDto deleteById(long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Trip not found with id  "  + id));
        Vehicle vehicle = vehicleRepository.findById(trip.getVehicle().getId()).orElseThrow(()-> new EntityNotFoundException("Vehicle not found with id  "  + trip.getVehicle().getId()));
        vehicle.setOdometer(vehicle.getOdometer() - trip.getDistance());
        tripRepository.delete(trip);
        return tripMapper.toDto(trip);
    }

    @Override
    public Double getSpendings() {
        return tripRepository.findAll().stream().map(Trip::getTripCost).reduce(0.0, Double::sum);
    }
}

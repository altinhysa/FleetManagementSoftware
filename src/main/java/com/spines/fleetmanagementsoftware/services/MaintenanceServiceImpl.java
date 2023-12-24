package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.Vehicle;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;
import com.spines.fleetmanagementsoftware.repositories.MaintenanceRepository;
import com.spines.fleetmanagementsoftware.repositories.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final ModelMapper modelMapper;
    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceServiceImpl(ModelMapper modelMapper, VehicleRepository vehicleRepository, MaintenanceRepository maintenanceRepository) {
        this.modelMapper = modelMapper;
        this.vehicleRepository = vehicleRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public MaintenanceDto createMaintenance(MaintenanceDto maintenance, long driverId) throws Exception {
        Optional<Vehicle> vehicle = vehicleRepository.findById(driverId);
        if (!vehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle not found!");
        }
        Maintenance savedMaintenance = modelMapper.map(maintenance,Maintenance.class);
        savedMaintenance.setVehicle(vehicle.get());
        savedMaintenance = maintenanceRepository.save(savedMaintenance);

        return modelMapper.map(savedMaintenance,MaintenanceDto.class);
    }
}

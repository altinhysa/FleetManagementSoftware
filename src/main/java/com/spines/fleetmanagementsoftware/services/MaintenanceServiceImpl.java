package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.Vehicle;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;
import com.spines.fleetmanagementsoftware.repositories.MaintenanceRepository;
import com.spines.fleetmanagementsoftware.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService{

    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceServiceImpl(VehicleRepository vehicleRepository, MaintenanceRepository maintenanceRepository) {
        this.vehicleRepository = vehicleRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public MaintenanceDto createMaintenance(Maintenance maintenance, long driverId) throws Exception {
        Optional<Vehicle> vehicle = vehicleRepository.findById(driverId);
        if (!vehicle.isPresent()){
            throw new Exception("Vehicle does not exist");
        }
        maintenance.setVehicle(vehicle.get());
        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        MaintenanceDto maintenanceDto = MaintenanceDto.builder()
                .id(savedMaintenance.getId())
                .comment(savedMaintenance.getComment())
                .maintenanceType(savedMaintenance.getMaintenanceType())
                .workshop(savedMaintenance.getWorkshop())
                .build();

        return maintenanceDto;
    }
}

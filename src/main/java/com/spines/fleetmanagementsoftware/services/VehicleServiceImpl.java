package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.exceptions.VehicleDoesNotExistException;
import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.Vehicle;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;
import com.spines.fleetmanagementsoftware.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<MaintenanceDto> findMaintenancesByVehicleId(long id) {
        List<Maintenance> maintenances = vehicleRepository.findById(id).get().getMaintenances();
        List<MaintenanceDto> maintenanceDtos = new ArrayList<>();

        for (Maintenance maintenance : maintenances){
            maintenanceDtos.add(MaintenanceDto.builder()
                    .id(maintenance.getId())
                    .workshop(maintenance.getWorkshop())
                    .comment(maintenance.getComment())
                    .maintenanceType(maintenance.getMaintenanceType())
                    .date(maintenance.getDate())
                    .build());
        }

        return maintenanceDtos;
    }

    @Override
    public Driver assignDriver(Driver driver, long vehicleId) throws Exception {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()){
            throw new VehicleDoesNotExistException("Vehicle does not exist");
        }
        Vehicle updatedVehicle = vehicle.get();
        updatedVehicle.setDriver(driver);
        return vehicleRepository.save(updatedVehicle).getDriver();
    }
}

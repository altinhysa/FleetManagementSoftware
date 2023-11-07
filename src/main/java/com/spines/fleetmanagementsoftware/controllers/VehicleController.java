package com.spines.fleetmanagementsoftware.controllers;

import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;
import com.spines.fleetmanagementsoftware.services.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{id}/maintenances")
    public List<MaintenanceDto> getMaintenancesById(@PathVariable long id){
        return vehicleService.findMaintenancesByVehicleId(id);
    }

    @PutMapping("/{id}/driver")
    public Driver addDriverToVehicle(@RequestBody Driver driver, @PathVariable long id) throws Exception {
        return vehicleService.assignDriver(driver,id);
    }
}

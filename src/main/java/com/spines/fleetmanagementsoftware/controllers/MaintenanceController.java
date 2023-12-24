package com.spines.fleetmanagementsoftware.controllers;

import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;
import com.spines.fleetmanagementsoftware.services.MaintenanceService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("maintenances")
@CrossOrigin(origins = "*")

public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public MaintenanceDto addMaintenance(@RequestBody MaintenanceDto maintenance, @RequestParam long vehicleId) throws Exception {
        return maintenanceService.createMaintenance(maintenance,vehicleId);
    }


}

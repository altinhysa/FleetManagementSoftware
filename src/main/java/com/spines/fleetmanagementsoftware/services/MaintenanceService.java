package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;

public interface MaintenanceService {

    MaintenanceDto createMaintenance(Maintenance maintenance, long driverId) throws Exception;
}
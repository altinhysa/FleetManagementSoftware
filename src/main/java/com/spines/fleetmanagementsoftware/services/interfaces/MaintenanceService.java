package com.spines.fleetmanagementsoftware.services.interfaces;

import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;

public interface MaintenanceService {

    MaintenanceDto createMaintenance(MaintenanceDto maintenance, long driverId) throws Exception;
}

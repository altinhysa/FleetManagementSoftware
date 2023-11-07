package com.spines.fleetmanagementsoftware.services;


import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;

import java.util.List;

public interface VehicleService {
    List<MaintenanceDto> findMaintenancesByVehicleId(long id);

    Driver assignDriver(Driver driver, long vehicleId) throws Exception;

}

package com.spines.fleetmanagementsoftware.services.interfaces;


import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.models.VehicleStatus;
import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;
import com.spines.fleetmanagementsoftware.models.dtos.VehicleDto;

import java.util.List;
import java.util.Map;

public interface VehicleService extends CrudService<VehicleDto,Long>{
    List<MaintenanceDto> findMaintenancesByVehicleId(long id) throws VehicleNotFoundException;

    DriverDto assignDriver(int driverId, long vehicleId) throws Exception;

    Map<VehicleStatus, Long> groupVehiclesCountByStatuses();

    List<VehicleDto> getAvailableVehicles();
}

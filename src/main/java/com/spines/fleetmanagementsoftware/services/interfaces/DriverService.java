package com.spines.fleetmanagementsoftware.services.interfaces;

import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import java.util.List;
public interface DriverService extends CrudService<DriverDto,Integer> {
    List<DriverDto> getAvailableDrivers();
}

package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface DriverService extends CrudService<DriverDto,Integer> {

}

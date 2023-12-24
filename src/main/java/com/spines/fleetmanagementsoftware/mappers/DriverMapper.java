package com.spines.fleetmanagementsoftware.mappers;

import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DriverMapper extends com.spines.fleetmanagementsoftware.mappers.Mapper<Driver, DriverDto> {
    DriverMapper MAPPER = Mappers.getMapper(DriverMapper.class);
}

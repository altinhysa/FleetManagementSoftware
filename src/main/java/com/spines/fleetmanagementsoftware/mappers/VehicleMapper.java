package com.spines.fleetmanagementsoftware.mappers;

import com.spines.fleetmanagementsoftware.models.Vehicle;
import com.spines.fleetmanagementsoftware.models.dtos.VehicleDto;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(componentModel = "spring")
public interface VehicleMapper extends Mapper<Vehicle, VehicleDto> {
    VehicleMapper MAPPER = Mappers.getMapper(VehicleMapper.class);

    @Override
    @Mapping(ignore = true,target = "driver")
    @Mapping(ignore = true,target = "maintenances")
    @Mapping(source = "brand", target = "brand")
    Vehicle toEntity(VehicleDto dto);

    @Override
    @Mapping(ignore = true,target = "driver")
    @Mapping(source = "brand", target = "brand")
    VehicleDto toDto(Vehicle entity);
}

package com.spines.fleetmanagementsoftware.mappers;

import com.spines.fleetmanagementsoftware.models.Trip;
import com.spines.fleetmanagementsoftware.models.dtos.TripDto;
import org.springframework.stereotype.Component;


@Component
public class TripMapper implements Mapper<Trip, TripDto> {
    @Override
    public Trip toEntity(TripDto dto) {
        Trip trip = new Trip();
        trip.setDestination(dto.getDestination());
        trip.setDeparture(dto.getDeparture());
        trip.setTripCost(dto.getTripCost());
        trip.setId(dto.getId());
        trip.setDistance(dto.getDistance());
        trip.setEndDate(dto.getEndDate());
        trip.setEndTime(dto.getEndTime());
        trip.setStartDate(dto.getStartDate());
        trip.setStartTime(dto.getStartTime());
        trip.setFuelPrice(dto.getFuelPrice());
        trip.setFuelConsumption(dto.getFuelConsumption());
        trip.setFuelUsed(dto.getFuelUsed());
        return trip;
    }

    @Override
    public TripDto toDto(Trip entity) {
        TripDto dto = new TripDto();
        dto.setVehicleId(entity.getVehicle().getId());
        dto.setVehicleName(entity.getVehicle().getBrand());
        dto.setDestination(entity.getDestination());
        dto.setDeparture(entity.getDeparture());
        dto.setTripCost(entity.getTripCost());
        dto.setId(entity.getId());
        dto.setDistance(entity.getDistance());
        dto.setEndDate(entity.getEndDate());
        dto.setEndTime(entity.getEndTime());
        dto.setStartDate(entity.getStartDate());
        dto.setStartTime(entity.getStartTime());
        dto.setFuelPrice(entity.getFuelPrice());
        dto.setFuelConsumption(entity.getFuelConsumption());
        dto.setFuelUsed(entity.getFuelUsed());
        return dto;
    }
}

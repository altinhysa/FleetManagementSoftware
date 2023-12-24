package com.spines.fleetmanagementsoftware.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spines.fleetmanagementsoftware.models.Vehicle;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
@Data
public class TripDto {
    private Long id;

    private Date startDate;

    private Date endDate;

    private Time startTime;

    private Time endTime;

    private Long vehicleId;

    private String vehicleName;

    private String departure;
    private String destination;
    private long distance;

    private double fuelConsumption;

    private double fuelUsed;

    private double fuelPrice;

    private double tripCost;
}

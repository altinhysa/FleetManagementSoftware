package com.spines.fleetmanagementsoftware.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonIgnore
    @ManyToOne
    private Vehicle vehicle;

    private Date startDate;

    private Date endDate;

    private Time startTime;

    private Time endTime;

    private String departure;
    private String destination;
    private long distance;

    private double fuelConsumption;

    private double fuelUsed;

    private double fuelPrice;

    private double tripCost;
}

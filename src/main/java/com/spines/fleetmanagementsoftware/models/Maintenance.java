package com.spines.fleetmanagementsoftware.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;

@Data
@Entity
@Table(name = "maintenances")
public class Maintenance{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private Date date;

    @Enumerated(EnumType.STRING)
    private MaintenanceType maintenanceType;

    private String workshop;

    private String comment;

    @CreatedDate
    public Date createdDate;

    @LastModifiedDate
    public Date lastModifiedDate;

    public Double cost;
}

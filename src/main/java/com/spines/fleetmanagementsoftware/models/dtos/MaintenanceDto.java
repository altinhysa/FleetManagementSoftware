package com.spines.fleetmanagementsoftware.models.dtos;

import com.spines.fleetmanagementsoftware.models.MaintenanceType;
import com.spines.fleetmanagementsoftware.models.Vehicle;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class MaintenanceDto {
    private Integer id;

    private Date date;

    private MaintenanceType maintenanceType;

    private String workshop;

    private String comment;
    public Double cost;

}

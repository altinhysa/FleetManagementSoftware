package com.spines.fleetmanagementsoftware.models.dtos;

import com.spines.fleetmanagementsoftware.models.MaintenanceType;
import com.spines.fleetmanagementsoftware.models.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDto {
    private Integer id;

    @NotBlank(message = "Invalid Date: Empty date")
    @NotNull(message = "Invalid Date: Date is NULL")
    private Date date;

    @NotBlank(message = "Invalid Maintenance Type: Empty Maintenance Type")
    @NotNull(message = "Invalid Maintenance Type: Maintenance Type is NULL")
    private MaintenanceType maintenanceType;

    private String workshop;

    private String comment;
    public Double cost;

}

package com.spines.fleetmanagementsoftware.models.dtos;

import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.Fuel;
import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.VehicleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {

    private Long id;
    @NotBlank(message = "Invalid Brand: Empty Brand")
    @NotNull(message = "Invalid Brand: Brand is NULL")
    private String brand;
    private String color;
    private Fuel fuel;
    private VehicleStatus status;
    private String driver;

    @Pattern(regexp = "^(0[1-8])-\\d{3}-[A-Z]{2}$", message = "Invalid Pattern: Must match this pattern 01-123-GF")
    private String licencePlate;

    @Min(value = 0, message = "Invalid Number: odomoter must be higher or equal to 0")
    private long odometer;

    @Min(value = 2000, message = "Invalid Year: Year must be higher than 2000")
    @Max(value = 2024, message = "Invalid Year")
    private short yearOfProduced;
    private long serviceDistance;
    private boolean active;
}

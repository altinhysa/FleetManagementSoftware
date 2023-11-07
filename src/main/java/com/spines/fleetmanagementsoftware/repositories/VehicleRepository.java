package com.spines.fleetmanagementsoftware.repositories;

import com.spines.fleetmanagementsoftware.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}

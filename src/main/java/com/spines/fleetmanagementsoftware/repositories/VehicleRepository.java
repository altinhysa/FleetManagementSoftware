package com.spines.fleetmanagementsoftware.repositories;

import com.spines.fleetmanagementsoftware.models.Vehicle;
import com.spines.fleetmanagementsoftware.models.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    List<Vehicle> findAllByStatus(VehicleStatus status);
}

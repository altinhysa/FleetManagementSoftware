package com.spines.fleetmanagementsoftware.repositories;

import com.spines.fleetmanagementsoftware.models.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
}

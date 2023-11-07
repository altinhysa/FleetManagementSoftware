package com.spines.fleetmanagementsoftware.repositories;

import com.spines.fleetmanagementsoftware.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
}

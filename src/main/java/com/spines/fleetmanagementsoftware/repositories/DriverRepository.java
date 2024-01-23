package com.spines.fleetmanagementsoftware.repositories;

import com.spines.fleetmanagementsoftware.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DriverRepository extends JpaRepository<Driver,Integer> {
    List<Driver> getDriversByAvailable(boolean available);
}

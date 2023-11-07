package com.spines.fleetmanagementsoftware.repositories;


import com.spines.fleetmanagementsoftware.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {


}

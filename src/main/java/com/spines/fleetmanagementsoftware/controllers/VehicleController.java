package com.spines.fleetmanagementsoftware.controllers;

import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.VehicleStatus;
import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;
import com.spines.fleetmanagementsoftware.models.dtos.VehicleDto;
import com.spines.fleetmanagementsoftware.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{id}/maintenances")
    public List<MaintenanceDto> getMaintenancesById(@PathVariable long id) throws VehicleNotFoundException {
        return vehicleService.findMaintenancesByVehicleId(id);
    }

    @GetMapping
    public Page<VehicleDto> getAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int sizePerPage,
                                   @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection){
        return vehicleService.findAllByPage(page,sizePerPage,sortDirection);
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicleById(@PathVariable long id){
        return vehicleService.getById(id);
    }

    @PostMapping
    public VehicleDto add(@RequestBody @Valid VehicleDto vehicleDto){
        return vehicleService.add(vehicleDto);
    }

    @PutMapping("/{id}")
    public VehicleDto update(@PathVariable Long id, @RequestBody VehicleDto vehicleDto){
        return vehicleService.update(id,vehicleDto);
    }


    @PatchMapping("/{id}/driver")
    public DriverDto addDriverToVehicle(@RequestParam int driverId, @PathVariable long id) throws Exception {
        return vehicleService.assignDriver(driverId,id);
    }

    @DeleteMapping("/{id}")
    public void deleteDriverById(@PathVariable long id){
        vehicleService.remove(id);
    }

    @GetMapping("/statusCount")
    public Map<VehicleStatus, Long> vehicleStatusLongMap(){
        return vehicleService.groupVehiclesCountByStatuses();
    }
}

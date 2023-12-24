package com.spines.fleetmanagementsoftware.controllers;


import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import com.spines.fleetmanagementsoftware.services.DriverService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "*")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public DriverDto addDriver(@RequestBody @Valid DriverDto driverDto) {
        return driverService.add(driverDto);
    }

    @GetMapping("/{id}")
    public DriverDto getById(@PathVariable int id) {
        return driverService.getById(id);
    }

    @GetMapping
    public Page<DriverDto> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int sizePerPage,
                                  @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection){

        return driverService.findAllByPage(page,sizePerPage,sortDirection);
    }

    @PutMapping("/{id}")
    public DriverDto update(@PathVariable int id, @RequestBody @Valid DriverDto driverDto){
        return driverService.update(id,driverDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        driverService.remove(id);
    }
}

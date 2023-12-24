package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.exceptions.VehicleDoesNotExistException;
import com.spines.fleetmanagementsoftware.exceptions.VehicleNotFoundException;
import com.spines.fleetmanagementsoftware.mappers.Mapper;
import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.Maintenance;
import com.spines.fleetmanagementsoftware.models.Vehicle;
import com.spines.fleetmanagementsoftware.models.VehicleStatus;
import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import com.spines.fleetmanagementsoftware.models.dtos.MaintenanceDto;
import com.spines.fleetmanagementsoftware.models.dtos.VehicleDto;
import com.spines.fleetmanagementsoftware.repositories.DriverRepository;
import com.spines.fleetmanagementsoftware.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final ModelMapper modelMapper;
    private final VehicleRepository vehicleRepository;

    private final DriverRepository driverRepository;
    private final Mapper<Vehicle,VehicleDto> mapper;

    public VehicleServiceImpl(ModelMapper modelMapper, VehicleRepository vehicleRepository, DriverRepository driverRepository, @Qualifier("vehicleMapperImpl") Mapper<Vehicle, VehicleDto> mapper) {
        this.modelMapper = modelMapper;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MaintenanceDto> findMaintenancesByVehicleId(long id) throws VehicleNotFoundException {
        List<Maintenance> maintenances = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new).getMaintenances();

        return maintenances.stream().map(maintenance -> modelMapper.map(maintenance,MaintenanceDto.class)).toList();
    }

    @Override
    public DriverDto assignDriver(int driverId, long vehicleId) throws Exception {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isEmpty()) {
            throw new VehicleDoesNotExistException("Vehicle does not exist");
        }

        Vehicle updatedVehicle = vehicle.get();
        Driver driver = driverRepository.findById(driverId).orElseThrow(()->new EntityNotFoundException("Driver does not exist"));
        if(!driver.isAvailable()){
            throw new RuntimeException("Driver is not available");
        }

        driver.setAvailable(false);
        updatedVehicle.setDriver(driver);
        vehicleRepository.save(updatedVehicle);
        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public VehicleDto getById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Vehicle does not exist"));
        VehicleDto vehicleDto = mapper.toDto(vehicle);
        if(vehicle.getDriver() != null){
            vehicleDto.setDriver(vehicle.getDriver().getName() + " " + vehicle.getDriver().getSurname());
        }
        return vehicleDto;
    }

    @Override
    public Page<VehicleDto> findAllByPage(int page, int sizePerPage, Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page,sizePerPage);
        return vehicleRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public VehicleDto add(VehicleDto dto) {
        return mapper.toDto(vehicleRepository.save(mapper.toEntity(dto)));
    }

    @Override
    public VehicleDto update(Long id, VehicleDto dto) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        vehicle.setOdometer(dto.getOdometer());
        vehicle.setActive(dto.isActive());
        vehicle.setStatus(dto.getStatus());
        vehicle.setBrand(dto.getBrand());
        vehicle.setFuel(dto.getFuel());
        vehicle.setColor(dto.getColor());
        vehicle.setLicencePlate(dto.getLicencePlate());
        vehicle.setYearOfProduced(dto.getYearOfProduced());
        return mapper.toDto(vehicleRepository.save(vehicle));
    }

    @Override
    public void remove(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<VehicleDto> getAll() {
        return vehicleRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Map<VehicleStatus, Long> groupVehiclesCountByStatuses() {
        return vehicleRepository.findAll().stream().collect(Collectors.groupingBy(Vehicle::getStatus,Collectors.counting()));
    }
}

package com.spines.fleetmanagementsoftware.services;

import com.spines.fleetmanagementsoftware.mappers.Mapper;
import com.spines.fleetmanagementsoftware.models.Driver;
import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import com.spines.fleetmanagementsoftware.repositories.DriverRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    private final Mapper<Driver, DriverDto> mapper;

    public DriverServiceImpl(DriverRepository driverRepository, @Qualifier("driverMapperImpl") Mapper<Driver, DriverDto> mapper) {
        this.driverRepository = driverRepository;
        this.mapper = mapper;

    }

    @Override
    public DriverDto add(DriverDto dto) {
        Driver driver = mapper.toEntity(dto);
        return mapper.toDto(driverRepository.save(driver));
    }

    @Override
    public DriverDto update(Integer id, DriverDto dto) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver not found"));

        Driver updatedDriver = mapper.toEntity(dto);
        updatedDriver.setId(id);
        return mapper.toDto(driverRepository.save(updatedDriver));
    }

    @Override
    public void remove(Integer id) {
        driverRepository.deleteById(id);
    }

    @Override
    public List<DriverDto> getAll() {
        return driverRepository.findAll().stream().map(mapper::toDto).toList();
    }


    public Page<DriverDto> findAllByPage(int page, int sizePerPage, Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page, sizePerPage);
        return driverRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public DriverDto getById(Integer id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver with id not found"));
        System.out.println(driver);
        return mapper.toDto(driver);
    }
}

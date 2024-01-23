package com.spines.fleetmanagementsoftware.services.interfaces;

import com.spines.fleetmanagementsoftware.models.dtos.DriverDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CrudService<D,T> {
    D add(D dto);
    D update(T id,D dto);
    void remove(T id);
    List<D> getAll();
    D getById(T id);

    Page<D> findAllByPage(int page, int sizePerPage, Sort.Direction sortDirection);

}

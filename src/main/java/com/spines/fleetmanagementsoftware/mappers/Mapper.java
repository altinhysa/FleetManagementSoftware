package com.spines.fleetmanagementsoftware.mappers;

public interface Mapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}

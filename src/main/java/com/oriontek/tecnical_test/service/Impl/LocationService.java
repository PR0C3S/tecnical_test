package com.oriontek.tecnical_test.service.Impl;

import com.oriontek.tecnical_test.dto.LocationDto;
import com.oriontek.tecnical_test.exception.BadRequestException;
import com.oriontek.tecnical_test.exception.DuplicatedDataException;
import com.oriontek.tecnical_test.exception.NotFoundException;
import com.oriontek.tecnical_test.model.Location;

import java.util.Set;

public interface LocationService {
    public Boolean existsById(Long id);

    public Location findById(Long id) throws NotFoundException;

    public Location create(Long customerId, LocationDto locationDto) throws NotFoundException, BadRequestException, DuplicatedDataException;

    public Location update(Long id, LocationDto locationDto) throws NotFoundException, BadRequestException, DuplicatedDataException;

    public void delete(Long id) throws NotFoundException;

    public Set<Location> findAll();
}

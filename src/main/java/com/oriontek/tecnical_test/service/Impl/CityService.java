package com.oriontek.tecnical_test.service.Impl;

import com.oriontek.tecnical_test.dto.CityDto;
import com.oriontek.tecnical_test.exception.BadRequestException;
import com.oriontek.tecnical_test.exception.DuplicatedDataException;
import com.oriontek.tecnical_test.exception.NotFoundException;
import com.oriontek.tecnical_test.model.City;
import com.oriontek.tecnical_test.model.Country;

import java.util.Set;

public interface CityService {
    public Boolean existsById(Long id);
    Boolean existsByNameAndCountry(String name, Country country);
    public City findById(Long id) throws NotFoundException;
    public City create(CityDto cityDto) throws NotFoundException, BadRequestException, DuplicatedDataException;
    public City update(Long id,CityDto cityDto) throws NotFoundException, BadRequestException, DuplicatedDataException;
    public void delete(Long id) throws NotFoundException;
    public Set<City> findAll();
}

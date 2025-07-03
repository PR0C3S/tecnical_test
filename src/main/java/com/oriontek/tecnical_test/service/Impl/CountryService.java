package com.oriontek.tecnical_test.service.Impl;

import com.oriontek.tecnical_test.dto.CountryDto;
import com.oriontek.tecnical_test.exception.BadRequestException;
import com.oriontek.tecnical_test.exception.DuplicatedDataException;
import com.oriontek.tecnical_test.exception.NotFoundException;
import com.oriontek.tecnical_test.model.Country;

import java.util.Set;

public interface CountryService {
    public Boolean existsById(Long id);
    public Boolean existsByName(String name);
    public Country findById(Long id) throws BadRequestException, NotFoundException;
    public Country create(CountryDto countryDto) throws BadRequestException, DuplicatedDataException;
    public Country update(Long id,CountryDto countryDto) throws NotFoundException, BadRequestException, DuplicatedDataException;
    public void delete(Long id) throws NotFoundException;
    public Set<Country> findAll();
}

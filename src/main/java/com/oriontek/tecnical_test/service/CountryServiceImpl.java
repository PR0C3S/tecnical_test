package com.oriontek.tecnical_test.service;

import com.oriontek.tecnical_test.dto.CountryDto;
import com.oriontek.tecnical_test.exception.DuplicatedDataException;
import com.oriontek.tecnical_test.exception.NotFoundException;
import com.oriontek.tecnical_test.model.Country;
import com.oriontek.tecnical_test.repository.CountryRepository;
import com.oriontek.tecnical_test.service.Impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository repository;

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public Country findById(Long id) throws  NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Country not found with id: " + id));
    }

    @Override
    public Country create(CountryDto countryDto) throws DuplicatedDataException {
        String name = countryDto.getName().toUpperCase();
        if(existsByName(name)){
            throw new DuplicatedDataException("Country with name '" + name + "' already exists.");
        }

        Country country = new Country();
        country.setName(name);
        return repository.save(country);
    }

    @Override
    public Country update(Long id, CountryDto countryDto) throws NotFoundException, DuplicatedDataException {

        Country country = findById(id);
        String name = countryDto.getName().toUpperCase();

        if(!country.getName().equalsIgnoreCase(name) && existsByName(name)){
            throw new DuplicatedDataException("Country with name '" + name + "' already exists.");
        }

        country.setName(name);
        return repository.save(country);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if(!existsById(id)){
            throw new NotFoundException("Country not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Set<Country> findAll() {
        return new HashSet<>(repository.findAll());
    }
}

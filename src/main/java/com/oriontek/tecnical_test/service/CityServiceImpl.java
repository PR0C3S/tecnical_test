package com.oriontek.tecnical_test.service;


import com.oriontek.tecnical_test.dto.CityDto;
import com.oriontek.tecnical_test.exception.DuplicatedDataException;
import com.oriontek.tecnical_test.exception.NotFoundException;
import com.oriontek.tecnical_test.model.City;
import com.oriontek.tecnical_test.model.Country;
import com.oriontek.tecnical_test.repository.CityRepository;
import com.oriontek.tecnical_test.service.Impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository repository;

    @Autowired
    private CountryServiceImpl countryService;

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Boolean existsByNameAndCountry(String name,Country country) {
        return repository.existsByNameAndCountry(name,country);
    }

    @Override
    public City findById(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("City not found with id: " + id));
    }

    @Override
    public City create(CityDto cityDto) throws NotFoundException, DuplicatedDataException {
        Country country = countryService.findById(cityDto.getCountryId());
        String name = cityDto.getName().toUpperCase();

        if(existsByNameAndCountry(name,country)){
            throw new DuplicatedDataException("City with name '" + name + "' already exists in country: " + country.getName());
        }
        City city = new City();
        city.setName(name);
        city.setCountry(country);
        return repository.save(city);
    }

    @Override
    public City update(Long id, CityDto cityDto) throws NotFoundException, DuplicatedDataException {
        City city = findById(id);

        String name = cityDto.getName().toUpperCase();
        Country country = countryService.findById(cityDto.getCountryId());
        if(!city.getName().equalsIgnoreCase(name) && existsByNameAndCountry(name,country)){
            throw new DuplicatedDataException("City with name '" + name + "' already exists in country: " + country.getName());
        }

       city.setCountry(country);
        city.setName(name);
        return repository.save(city);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if(!existsById(id)){
            throw new NotFoundException("City not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Set<City> findAll() {
        return new HashSet<>(repository.findAll());
    }
}

package com.oriontek.tecnical_test.service;

import com.oriontek.tecnical_test.dto.LocationDto;
import com.oriontek.tecnical_test.enums.TypeHouse;
import com.oriontek.tecnical_test.exception.BadRequestException;
import com.oriontek.tecnical_test.exception.DuplicatedDataException;
import com.oriontek.tecnical_test.exception.NotFoundException;
import com.oriontek.tecnical_test.model.City;
import com.oriontek.tecnical_test.model.Customer;
import com.oriontek.tecnical_test.model.Location;
import com.oriontek.tecnical_test.repository.LocationRepository;
import com.oriontek.tecnical_test.service.Impl.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Location findById(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Location not found with id: " + id));
    }

    @Override
    public Location create(Long customerId, LocationDto locationDto) throws NotFoundException, BadRequestException {
        Customer customer = customerService.findById(customerId);
        City city = cityService.findById(locationDto.getCityId());

        Location location = new Location();

        TypeHouse typeHouse = TypeHouse.valueOf(locationDto.getTypeOfHouse());

        if (typeHouse.equals(TypeHouse.HOUSE)) {
            if (locationDto.getHouseNumber().isBlank()) {
                throw new BadRequestException("House number is required for type HOUSE.");
            } else {
                location.setHouseNumber(locationDto.getHouseNumber());
                location.setBuildingName(null);
                location.setUnitNumber(null);
            }
        } else {
            if (locationDto.getBuildingName().isBlank()) {
                throw new BadRequestException("Building name is required for type APARTMENT or LOCAL.");
            } else if (locationDto.getUnitNumber().isBlank()) {
                throw new BadRequestException("Unit number is required for type APARTMENT or LOCAL.");
            } else {
                location.setHouseNumber(null);
                location.setBuildingName(locationDto.getBuildingName().toUpperCase());
                location.setUnitNumber(locationDto.getUnitNumber().toUpperCase());
            }
        }

        location.setCity(city);
        location.setZone(locationDto.getZone().toUpperCase());
        location.setStreet(locationDto.getStreet().toUpperCase());
        location.setTypeOfHouse(typeHouse);
        location.setCustomer(customer);
        return repository.save(location);
    }

    @Override
    public Location update(Long id, LocationDto locationDto) throws NotFoundException, BadRequestException, DuplicatedDataException {
        City city = cityService.findById(locationDto.getCityId());
        Location location = findById(id);

        TypeHouse typeHouse = TypeHouse.valueOf(locationDto.getTypeOfHouse());

        if (typeHouse.equals(TypeHouse.HOUSE)) {
            if (locationDto.getHouseNumber().isBlank()) {
                throw new BadRequestException("House number is required for type HOUSE.");
            } else {
                location.setHouseNumber(locationDto.getHouseNumber());
                location.setBuildingName(null);
                location.setUnitNumber(null);
            }
        } else {
            if (locationDto.getBuildingName().isBlank()) {
                throw new BadRequestException("Building name is required for type APARTMENT or LOCAL.");
            } else if (locationDto.getUnitNumber().isBlank()) {
                throw new BadRequestException("Unit number is required for type APARTMENT or LOCAL.");
            } else {
                location.setHouseNumber(null);
                location.setBuildingName(locationDto.getBuildingName().toUpperCase());
                location.setUnitNumber(locationDto.getUnitNumber().toUpperCase());
            }
        }

        // Don't touch the customer – keep existing
        location.setCity(city);
        location.setZone(locationDto.getZone().toUpperCase());
        location.setStreet(locationDto.getStreet().toUpperCase());
        location.setTypeOfHouse(typeHouse);
        return repository.save(location);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Location with id " + id + " not found.");
        }
        repository.deleteById(id);
    }

    @Override
    public Set<Location> findAll() {
        return new HashSet<>(repository.findAll());
    }

}

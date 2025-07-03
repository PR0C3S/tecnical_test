package com.oriontek.tecnical_test.service;

import com.oriontek.tecnical_test.dto.CustomerDto;
import com.oriontek.tecnical_test.enums.TypeHouse;
import com.oriontek.tecnical_test.exception.BadRequestException;
import com.oriontek.tecnical_test.exception.DuplicatedDataException;
import com.oriontek.tecnical_test.exception.NotFoundException;
import com.oriontek.tecnical_test.model.City;
import com.oriontek.tecnical_test.model.Customer;
import com.oriontek.tecnical_test.model.Location;
import com.oriontek.tecnical_test.repository.CustomerRepository;
import com.oriontek.tecnical_test.service.Impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CityServiceImpl cityService;


    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }


    @Override
    public Customer findById(Long id) throws NotFoundException {
       return repository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));

    }

    @Override
    public Customer create(CustomerDto customerDto) throws NotFoundException, DuplicatedDataException {
        String email = customerDto.getEmail().toUpperCase();
        String phone = customerDto.getPhone().toUpperCase();

        if(existsByEmail(email)){
           throw new DuplicatedDataException("Customer with email '" + email + "' already exists.");
        }

        if(existsByPhone(phone)){
            throw new DuplicatedDataException("Customer with phone '" + phone + "' already exists.");
        }



        Customer customer = new Customer();

        customerDto.getLocations().forEach((locationDto -> {
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
            customer.getLocations().add(location);
        }));
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setFirstName(customerDto.getFirstName().toUpperCase());
        customer.setLastName(customerDto.getLastName().toUpperCase());
        return repository.save(customer);
    }

    @Override
    public Customer update(Long id, CustomerDto customerDto) throws NotFoundException, DuplicatedDataException {
        Customer customer = findById(id);
        String email = customerDto.getEmail().toUpperCase();
        String phone = customerDto.getPhone().toUpperCase();

        if(!customer.getEmail().equalsIgnoreCase(email) && existsByEmail(email)){
            throw new DuplicatedDataException("Customer with email '" + email + "' already exists.");
        }

        if(!customer.getPhone().equalsIgnoreCase(phone) && existsByPhone(phone)){
            throw new DuplicatedDataException("Customer with phone '" + phone + "' already exists.");
        }

        //not update locations
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setFirstName(customerDto.getFirstName().toUpperCase());
        customer.setLastName(customerDto.getLastName().toUpperCase());
        return repository.save(customer);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if(!existsById(id)){
            throw new NotFoundException("Customer not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Set<Customer> findAll() {
        return new HashSet<>(repository.findAll());
    }

}

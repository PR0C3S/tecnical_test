package com.oriontek.tecnical_test.service.Impl;

import com.oriontek.tecnical_test.dto.CustomerDto;
import com.oriontek.tecnical_test.exception.BadRequestException;
import com.oriontek.tecnical_test.exception.DuplicatedDataException;
import com.oriontek.tecnical_test.exception.NotFoundException;
import com.oriontek.tecnical_test.model.Customer;

import java.util.Set;

public interface CustomerService {
    public Boolean existsById(Long id);
    public Boolean existsByEmail(String email);
    public Boolean existsByPhone(String phone);
    public Customer findById(Long id) throws NotFoundException;
    public Customer create(CustomerDto customerDto) throws NotFoundException, DuplicatedDataException;
    public Customer update(Long id, CustomerDto customerDto) throws NotFoundException, BadRequestException, DuplicatedDataException;
    public void delete(Long id) throws NotFoundException;
    public Set<Customer> findAll();
}

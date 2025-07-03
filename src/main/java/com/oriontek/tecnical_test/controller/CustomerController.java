package com.oriontek.tecnical_test.controller;


import com.oriontek.tecnical_test.dto.CustomerDto;
import com.oriontek.tecnical_test.model.Customer;
import com.oriontek.tecnical_test.service.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customers", description = "Operations related to customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers")
    @GetMapping
    public ResponseEntity<Set<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @Operation(summary = "Get customer by ID", description = "Retrieve a customer by its unique identifier")
    @GetMapping("/{id}")
    private ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
            return ResponseEntity.ok(customerService.findById(id));
    }

    @Operation(summary = "Create a new customer", description = "Add a new customer to the system")
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
            return ResponseEntity.status(201).body(customerService.create(customerDto));
    }

    @Operation(summary = "Update an existing customer", description = "Modify the details of an existing customer")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id,@Valid @RequestBody CustomerDto customerDto) {
            return ResponseEntity.ok(customerService.update(id,customerDto));
    }

    @Operation(summary = "Delete a customer", description = "Remove a customer from the system by its unique identifier")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
            customerService.delete(id);
            return ResponseEntity.noContent().build();
    }


}

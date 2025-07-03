package com.oriontek.tecnical_test.controller;


import com.oriontek.tecnical_test.dto.CityDto;
import com.oriontek.tecnical_test.model.City;
import com.oriontek.tecnical_test.service.CityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/v1/cities")
@Tag(name = "Cities", description = "Operations related to cities")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @Operation(summary = "Get all cities", description = "Retrieve a list of all cities")
    @GetMapping
    public ResponseEntity<Set<City>> getAllCities() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @Operation(summary = "Get city by ID", description = "Retrieve a city by its unique identifier")
    @GetMapping("/{id}")
    private ResponseEntity<City> getCityById(@PathVariable("id") Long id) {
            return ResponseEntity.ok(cityService.findById(id));
    }

    @Operation(summary = "Create a new city", description = "Add a new city to the system")
    @PostMapping
    public ResponseEntity<City> createCity(@Valid @RequestBody CityDto cityDto) {
            return ResponseEntity.status(201).body(cityService.create(cityDto));
    }

    @Operation(summary = "Update an existing city", description = "Modify the details of an existing city")
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable("id") Long id,@Valid @RequestBody CityDto cityDto) {
            return ResponseEntity.ok(cityService.update(id,cityDto));
    }

    @Operation(summary = "Delete a city", description = "Remove a city from the system by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable("id") Long id) {
            cityService.delete(id);
            return ResponseEntity.noContent().build();
    }
}

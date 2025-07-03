package com.oriontek.tecnical_test.controller;


import com.oriontek.tecnical_test.dto.CountryDto;
import com.oriontek.tecnical_test.model.Country;
import com.oriontek.tecnical_test.service.CountryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/v1/countries")
@Tag(name = "Countries", description = "Operations related to countries")
public class CountryController {

    @Autowired
    private CountryServiceImpl countryService;

    @Operation(summary = "Get all countries", description = "Retrieve a list of all countries")
    @GetMapping
    public ResponseEntity<Set<Country>> getAllCountries() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @Operation(summary = "Get country by ID", description = "Retrieve a country by its unique identifier")
    @GetMapping("/{id}")
    private ResponseEntity<Country> getCountryById(@PathVariable("id") Long id) {
            return ResponseEntity.ok(countryService.findById(id));
    }

    @Operation(summary = "Create a new country", description = "Add a new country to the system")
    @PostMapping
    public ResponseEntity<Country> createCountry(@Valid @RequestBody CountryDto countryDto) {
            return ResponseEntity.status(201).body(countryService.create(countryDto));
    }

    @Operation(summary = "Update an existing country", description = "Modify the details of an existing country")
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable("id") Long id,@Valid @RequestBody CountryDto countryDto) {
            return ResponseEntity.ok(countryService.update(id,countryDto));
    }

    @Operation(summary = "Delete a country", description = "Remove a country from the system by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") Long id) {
            countryService.delete(id);
            return ResponseEntity.noContent().build();
    }
}

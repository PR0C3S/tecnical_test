package com.oriontek.tecnical_test.controller;

import com.oriontek.tecnical_test.dto.LocationDto;
import com.oriontek.tecnical_test.model.Location;
import com.oriontek.tecnical_test.service.LocationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/locations")
@Tag(name = "Locations", description = "Operations related to customer locations")
public class LocationController {

    @Autowired
    private LocationServiceImpl locationService;

    @Operation(summary = "Get all locations", description = "Retrieve a list of all locations")
    @GetMapping
    public ResponseEntity<Set<Location>> getAllLocations() {
        return ResponseEntity.status(201).body(locationService.findAll());
    }

    @Operation(summary = "Get location by ID", description = "Retrieve a location by its unique identifier")
    @GetMapping("/{id}")
    private ResponseEntity<Location> getLocationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(locationService.findById(id));
    }


    @Operation(summary = "Add a location to a customer",description = "Create a new location for a specific customer")
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Location> addLocationToCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody LocationDto locationDto) {
        return ResponseEntity.status(201).body(locationService.create(customerId, locationDto));
    }

    @Operation(summary = "Update a location by ID",description = "Modify the details of an existing location")
    @PutMapping("/{locationId}")
    public ResponseEntity<Location> updateLocation(
            @PathVariable Long locationId,
            @Valid @RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(locationService.update(locationId, locationDto));
    }

    @Operation(summary = "Delete a location by ID",description = "Remove a location from the system by its unique identifier")
    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long locationId) {
        locationService.delete(locationId);
        return ResponseEntity.noContent().build();
    }
}

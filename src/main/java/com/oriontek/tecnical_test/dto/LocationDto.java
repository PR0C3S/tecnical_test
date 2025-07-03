package com.oriontek.tecnical_test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationDto {

    @NotNull(message = "City ID is required.")
    private Long cityId;

    @NotBlank(message = "Zone is required.")
    private String zone;

    @NotBlank(message = "Street is required.")
    private String street;

    @NotBlank(message = "Type of house is required.")
    private String typeOfHouse;

    // Optional fields
    private String houseNumber;
    private String buildingName;
    private String unitNumber;
}

package com.oriontek.tecnical_test.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CityDto {

    @NotBlank(message = "City name is required.")
    private String name;

    @NotNull(message = "Country ID is required.")
    private Long countryId;
}

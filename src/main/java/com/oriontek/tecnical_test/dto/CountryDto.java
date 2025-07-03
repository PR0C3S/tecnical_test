package com.oriontek.tecnical_test.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CountryDto {

    @NotBlank(message = "Country name is required.")
    private String name;
}

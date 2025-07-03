package com.oriontek.tecnical_test.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class CustomerDto {

    @NotBlank(message = "First Name is required.")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotBlank(message = "Phone is required.")
    private String phone;

    @NotNull(message = "Locations are required.")
    @Size(min = 1, message = "At least one location is required.")
    @Valid
    private Set<LocationDto> locations;
}

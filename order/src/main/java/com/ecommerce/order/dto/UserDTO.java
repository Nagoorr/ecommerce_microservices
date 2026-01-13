package com.ecommerce.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record UserDTO(Long Id,
                      @NotNull
                      String firstName,
                      @NotNull
                      String lastName,
                      @Email
                      @NotNull
                      String email,
                      @NotNull
                      Long mobileNumber,
                      AddressDTO addressDTO) {
}

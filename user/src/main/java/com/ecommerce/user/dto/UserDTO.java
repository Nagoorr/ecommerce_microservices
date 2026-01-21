package com.ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    String id;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @Email
    @NotNull
    String email;
    @NotNull
    Long mobileNumber;
    AddressDTO addressDTO;
}

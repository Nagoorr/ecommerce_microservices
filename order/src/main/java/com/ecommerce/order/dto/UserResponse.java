package com.ecommerce.order.dto;

import lombok.Data;

@Data
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    String email;
    Long mobileNumber;
    AddressDTO addressDTO;
}

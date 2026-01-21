package com.ecommerce.user.dto;

public record AddressDTO(String street,
                         String city,
                         Long pincode,
                         String country,
                         String state) {
}

package com.ecommerce.user.dto;

public record AddressDTO(Long id,
                         String street,
                         String city,
                         Long pincode,
                         String country,
                         String state) {
}

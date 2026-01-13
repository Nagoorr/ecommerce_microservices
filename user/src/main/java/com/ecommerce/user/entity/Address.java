package com.ecommerce.user.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class Address {
    private Long id;
    private String street;
    private String city;
    private Long pincode;
    private String country;
    private String state;
    private User user;
    @CreatedDate
    private LocalDateTime createdTimeStamp;
    @LastModifiedDate
    private LocalDateTime updatedTimeStamp;

}
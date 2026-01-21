package com.ecommerce.user.entity;

import com.ecommerce.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;
    private Long mobileNumber;
    private UserRole userRole = UserRole.BUYER;
    private Address address;
    @CreatedDate
    private LocalDateTime createdTimeStamp;
    @LastModifiedDate
    private LocalDateTime updatedTimeStamp;
}
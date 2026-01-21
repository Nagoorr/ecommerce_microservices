package com.ecommerce.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    Long productId;
    String productName;
    String description;
    BigDecimal price;
    Integer stockQuantity;
    String category;
    String imageUrl;
    Boolean active;
}

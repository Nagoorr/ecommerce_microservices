package com.ecommerce.order.dto;

import java.math.BigDecimal;

public record ProductDTO(Long productId,
        String productName,
         String description,
         BigDecimal price,
         Integer stockQuantity,
         String category,
         String imageUrl,
         Boolean active                ) {
}

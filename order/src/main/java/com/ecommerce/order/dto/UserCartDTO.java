package com.ecommerce.order.dto;

import java.math.BigDecimal;

public record UserCartDTO(Long cartId,
                          String userId,
                          Long productId,
                          Integer quantity,
                          BigDecimal price) {
}

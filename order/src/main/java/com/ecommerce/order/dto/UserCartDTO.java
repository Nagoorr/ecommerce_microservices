package com.ecommerce.order.dto;

import java.math.BigDecimal;

public record UserCartDTO(Long cartId,
                          Long userId,
                          Long productId,
                          Integer quantity,
                          BigDecimal price) {
}

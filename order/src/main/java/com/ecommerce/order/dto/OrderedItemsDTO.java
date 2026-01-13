package com.ecommerce.order.dto;


import java.math.BigDecimal;


public record OrderedItemsDTO(Long orderItemId,
                              Long productId,
                              Integer quantity,
                              BigDecimal price,
                              OrdersDTO ordersDTO) {
}

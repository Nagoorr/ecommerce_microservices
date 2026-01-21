package com.ecommerce.order.dto;

import com.ecommerce.order.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
public record OrdersDTO(Long orderId,
                        OrderStatus orderStatus,
                        String userId,
                        BigDecimal totalPrice,
                        List<OrderedItemsDTO> orderItemsList) {
}

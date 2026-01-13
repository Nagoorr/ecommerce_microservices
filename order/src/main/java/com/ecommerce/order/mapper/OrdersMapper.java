package com.ecommerce.order.mapper;

import com.ecommerce.order.entity.Orders;
import com.ecommerce.order.dto.OrdersDTO;

public class OrdersMapper {

    public static OrdersDTO toOrderDTO(Orders orders){
        return new OrdersDTO(orders.getOrderId(),orders.getOrderStatus(),orders.getUserId(),orders.getTotalPrice(),orders.getOrderItemsList().stream().map(OrderedItemsMapper::toOrderedItemsDTO).toList());
    }
}

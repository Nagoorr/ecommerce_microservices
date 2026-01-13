package com.ecommerce.order.mapper;

import com.ecommerce.order.entity.OrderedItems;
import com.ecommerce.order.dto.OrderedItemsDTO;

public class OrderedItemsMapper {
    public static OrderedItemsDTO toOrderedItemsDTO(OrderedItems orderedItems) {
        return new OrderedItemsDTO(orderedItems.getOrderItemId(),orderedItems.getProductId(),orderedItems.getQuantity(),orderedItems.getPrice(),OrdersMapper.toOrderDTO(orderedItems.getOrder()));
    }
}

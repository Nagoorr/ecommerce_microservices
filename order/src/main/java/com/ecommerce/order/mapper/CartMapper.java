package com.ecommerce.order.mapper;

import com.ecommerce.order.entity.UserCart;
import com.ecommerce.order.dto.UserCartDTO;

public class CartMapper {

    public static UserCart toCartEntity(UserCartDTO userCartDTO, UserCart userCart) {
        userCart.setUserId(userCartDTO.userId());
        userCart.setQuantity(userCartDTO.quantity());
        userCart.setProductId(userCartDTO.productId());
        userCart.setPrice(userCartDTO.price());
        return userCart;
    }
    public static UserCartDTO toCartDTOEntity( UserCart userCart) {
        return new UserCartDTO(userCart.getCartId(), userCart.getUserId(), userCart.getProductId(), userCart.getQuantity(),userCart.getPrice());
    }
}

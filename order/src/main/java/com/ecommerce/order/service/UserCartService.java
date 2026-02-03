package com.ecommerce.order.service;

import com.ecommerce.order.dto.CartDTO;
import com.ecommerce.order.dto.UserCartDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserCartService {
    boolean addUserToCart(String header , CartDTO cartDTO) throws Exception;

    void removeItems(String userId, Long id);

    UserCartDTO fetchCartItems(String userId, Long id);

    List<UserCartDTO> fetchCartByUser(String userId);
}

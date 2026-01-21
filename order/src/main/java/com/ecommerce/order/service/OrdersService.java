package com.ecommerce.order.service;

import java.util.concurrent.ExecutionException;

public interface OrdersService {
    boolean orderProduct(String userId) throws ExecutionException, InterruptedException;
}

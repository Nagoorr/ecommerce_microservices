package com.ecommerce.order.controller;

import com.ecommerce.order.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/orderItems")
    public ResponseEntity<Boolean> orderItems(@RequestHeader("X-User-ID") String userId) throws ExecutionException, InterruptedException {
        boolean result = ordersService.orderProduct(userId);
        return ResponseEntity.ok(result);
    }

}

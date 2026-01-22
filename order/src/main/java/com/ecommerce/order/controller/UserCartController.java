package com.ecommerce.order.controller;

import com.ecommerce.order.dto.CartDTO;
import com.ecommerce.order.dto.UserCartDTO;
import com.ecommerce.order.service.UserCartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/v1/cart")
public class UserCartController {

    @Autowired
    private UserCartService userCartService;

    @PostMapping("/addCartItems")
    public ResponseEntity<Boolean> addUserToCart(@RequestHeader("X-User-ID") String userId, @RequestBody @Valid CartDTO cartDTO) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userCartService.addUserToCart(userId, cartDTO));
    }

    @DeleteMapping("/removeItems/{cartId}")
    public ResponseEntity<Void> removeItems(@RequestHeader("X-User-ID") String userId, @PathVariable("cartId") Long cartId) {
        userCartService.removeItems(userId, cartId);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/fetchCartItems/{cartId}")
    public ResponseEntity<UserCartDTO> fetchCartItems(@RequestHeader("X-User-ID") String userId, @PathVariable("cartId") Long cartId) {
        return ResponseEntity.ok(userCartService.fetchCartItems(userId, cartId));
    }

    @GetMapping("/fetchCartByUser")
    public ResponseEntity<List<UserCartDTO>> fetchCartByUser(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(userCartService.fetchCartByUser(userId));
    }

}

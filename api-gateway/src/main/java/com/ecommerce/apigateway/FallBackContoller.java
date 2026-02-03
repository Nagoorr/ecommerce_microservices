package com.ecommerce.apigateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FallBackContoller {
    @PostMapping("/fallback/product")
    public ResponseEntity<List<String>> productFallBackCall() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(List.of("Temporarily product was not available try after sometime"));
    }

    @PostMapping("/fallback/order")
    public ResponseEntity<List<String>> orderFallBackCall() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(List.of("Temporarily order was not available try after sometime"));
    }
}

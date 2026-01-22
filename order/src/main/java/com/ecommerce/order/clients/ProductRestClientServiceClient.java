package com.ecommerce.order.clients;

import com.ecommerce.order.dto.ProductDTO;
import com.ecommerce.order.dto.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange
public interface ProductRestClientServiceClient {

    @GetExchange("/v1/products/findProductById/{id}")
    ProductResponse findProductById(@PathVariable String id);

    @PutExchange("/v1/products/updateProduct/{productId}")
    void updateProduct(@PathVariable String productId,@RequestBody ProductDTO productDTO);
}

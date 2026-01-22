package com.ecommerce.product.controller;

import com.ecommerce.product.dto.ProductDTO;
import com.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") Long productId, @RequestBody @Valid ProductDTO productDTO) {
        ProductDTO product = productService.updateProduct(productId, productDTO);
        if (product == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchProduct/{keyword}")
    public ResponseEntity<List<ProductDTO>> searchProduct(@PathVariable("keyword") String keyword){
        return ResponseEntity.ok(productService.searchProduct(keyword));
    }

    @GetMapping("/findProductById/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") String id){
        return ResponseEntity.ok(productService.findProductById(id));
    }
}

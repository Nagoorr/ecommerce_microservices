package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long productId ,ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> searchProduct(String keyword);
    ProductDTO findProductById(String id);
}

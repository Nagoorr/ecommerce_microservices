package com.ecommerce.product.mapper;


import com.ecommerce.product.dto.ProductDTO;
import com.ecommerce.product.entity.Product;

public class ProductMapper {
    public static ProductDTO toProductDTOEntity(Product product) {
        return new ProductDTO(product.getProductId(), product.getProductName(), product.getDescription(),
                product.getPrice(), product.getStockQuantity(), product.getCategory(), product.getImageUrl(),product.getActive());
    }

    public static Product toProductEntity(ProductDTO productDTO,Product product) {
        product.setProductName(productDTO.productName());
        product.setDescription(productDTO.description());
        product.setCategory(productDTO.category());
        product.setPrice(productDTO.price());
        product.setStockQuantity(productDTO.stockQuantity());
        product.setImageUrl(productDTO.imageUrl());
        product.setActive(productDTO.active());
        return product;
    }
}

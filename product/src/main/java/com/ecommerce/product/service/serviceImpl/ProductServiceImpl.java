package com.ecommerce.product.service.serviceImpl;

import com.ecommerce.product.service.ProductService;
import com.ecommerce.product.dto.ProductDTO;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        return ProductMapper.toProductDTOEntity(productRepo.save(ProductMapper.toProductEntity(productDTO, new Product())));
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        return productRepo.findById(productId).map(obj -> ProductMapper.toProductDTOEntity(ProductMapper.toProductEntity(productDTO, obj))).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.findById(id).map(product -> {
            product.setActive(false);
            productRepo.save(product);
            return product;
        });
    }

    @Override
    public List<ProductDTO> searchProduct(String keyword) {
        return productRepo.findByproductName(keyword).stream().map(ProductMapper::toProductDTOEntity).toList();
    }

    @Override
    public ProductDTO findProductById(String id) {
        return productRepo.findById(Long.parseLong(id)).map(ProductMapper::toProductDTOEntity).orElse(null);
    }
}

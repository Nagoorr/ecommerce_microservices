package com.ecommerce.product.repo;

import com.ecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.active=true and p.stockQuantity > 0 and LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName , '%'))")
    List<Product> findByproductName(String productName);
}

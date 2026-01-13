package com.ecommerce.order.repo;

import com.ecommerce.order.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCartRepo extends JpaRepository<UserCart,Long> {

    Optional<UserCart> findByUserIdAndProductId(Long userId, Long product);
    List<UserCart> findByUserId(Long userId);
    List<UserCart> deleteByUserId(Long userId);
}

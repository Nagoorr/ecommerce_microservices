package com.ecommerce.order.repo;

import com.ecommerce.order.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCartRepo extends JpaRepository<UserCart,Long> {

    Optional<UserCart> findByUserIdAndProductId(String userId, Long product);
    List<UserCart> findByUserId(String userId);
    List<UserCart> deleteByUserId(String userId);
}

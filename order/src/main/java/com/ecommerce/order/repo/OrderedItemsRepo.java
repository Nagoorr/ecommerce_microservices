package com.ecommerce.order.repo;

import com.ecommerce.order.entity.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemsRepo extends JpaRepository<OrderedItems,Long> {
}

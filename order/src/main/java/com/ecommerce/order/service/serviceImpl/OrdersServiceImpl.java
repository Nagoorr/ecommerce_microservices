package com.ecommerce.order.service.serviceImpl;

import com.ecommerce.order.service.OrdersService;
import com.ecommerce.order.entity.OrderedItems;
import com.ecommerce.order.entity.Orders;
import com.ecommerce.order.entity.UserCart;
import com.ecommerce.order.enums.OrderStatus;
import com.ecommerce.order.repo.OrderRepo;
import com.ecommerce.order.repo.OrderedItemsRepo;
import com.ecommerce.order.repo.UserCartRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserCartRepo userCartRepo;

    @Autowired
    private OrderedItemsRepo orderedItemsRepo;

    @Override
    public boolean orderProduct(String userId) {
        /*Optional<User> userOptional = userRepo.findById(Long.parseLong(userId));
        if (userOptional.isEmpty())
            return false;
        User user = userOptional.get();*/
        List<UserCart> userCartList = userCartRepo.findByUserId(Long.parseLong(userId));
        if (userCartList.isEmpty())
            return false;

        BigDecimal totalPrice = userCartList.stream().map(UserCart::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Orders orders = new Orders();
        orders.setOrderStatus(OrderStatus.CONFIRMED);
        orders.setUserId(Long.parseLong(userId));
        orders.setTotalPrice(totalPrice);
        Orders orders1 = orderRepo.save(orders);

      List<OrderedItems> orderedItemsList = userCartList.stream().map(obj -> {
            OrderedItems orderedItems = new OrderedItems();
            /*Product product = obj.getProduct();
            product.setStockQuantity(product.getStockQuantity() - obj.getQuantity());
            BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(obj.getQuantity()));
            productRepo.save(product);*/
            orderedItems.setQuantity(obj.getQuantity());
           // orderedItems.setProduct(product);
          // orderedItems.setPrice(price);
            orderedItems.setOrder(orders1);
            return orderedItems;
        }).toList();
      orderedItemsRepo.saveAll(orderedItemsList);
      userCartRepo.deleteByUserId(Long.parseLong(userId));
      return true;
    }
}

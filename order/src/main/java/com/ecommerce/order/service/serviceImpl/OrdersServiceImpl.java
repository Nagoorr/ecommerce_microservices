package com.ecommerce.order.service.serviceImpl;

import com.ecommerce.order.clients.ProductRestClientServiceClient;
import com.ecommerce.order.clients.UserWebClientService;
import com.ecommerce.order.dto.ProductDTO;
import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.dto.UserResponse;
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
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Transactional
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserCartRepo userCartRepo;

    @Autowired
    private OrderedItemsRepo orderedItemsRepo;

    @Autowired
    private UserWebClientService userWebClientService;

    @Autowired
    private ProductRestClientServiceClient productRestClientServiceClient;

    @Override
    public boolean orderProduct(String userId) throws ExecutionException, InterruptedException {
        Mono<UserResponse> mono = userWebClientService.findById(userId);
        UserResponse user = mono.toFuture().get();
        if (user == null) {
            return false;
        }

        List<UserCart> userCartList = userCartRepo.findByUserId(userId);
        if (userCartList.isEmpty())
            return false;

        BigDecimal totalPrice = userCartList.stream().map(UserCart::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Orders orders = new Orders();
        orders.setOrderStatus(OrderStatus.CONFIRMED);
        orders.setUserId(userId);
        orders.setTotalPrice(totalPrice);
        Orders orders1 = orderRepo.save(orders);

        List<OrderedItems> orderedItemsList = userCartList.stream().map(obj -> {
            OrderedItems orderedItems = new OrderedItems();
            ProductResponse product = productRestClientServiceClient.findProductById(String.valueOf(obj.getProductId()));
            product.setStockQuantity(product.getStockQuantity() - obj.getQuantity());
            BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(obj.getQuantity()));
            productRestClientServiceClient.updateProduct(product
                    .getProductId().toString(), new ProductDTO(product.getProductId(), product.getProductName()
                    , product.getDescription(), product.getPrice(), product.getStockQuantity(), product.getCategory(),
                    product.getImageUrl(), product.getActive()));
            orderedItems.setQuantity(obj.getQuantity());
            orderedItems.setProductId(product.getProductId());
            orderedItems.setPrice(price);
            orderedItems.setOrder(orders1);
            return orderedItems;
        }).toList();
        orderedItemsRepo.saveAll(orderedItemsList);
        userCartRepo.deleteByUserId(userId);
        return true;
    }
}

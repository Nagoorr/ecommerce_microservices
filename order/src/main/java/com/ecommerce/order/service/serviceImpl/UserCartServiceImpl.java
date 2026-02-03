package com.ecommerce.order.service.serviceImpl;


import com.ecommerce.order.clients.ProductRestClientServiceClient;
import com.ecommerce.order.clients.UserWebClientService;
import com.ecommerce.order.dto.*;
import com.ecommerce.order.service.UserCartService;
import com.ecommerce.order.entity.UserCart;
import com.ecommerce.order.mapper.CartMapper;
import com.ecommerce.order.repo.UserCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class UserCartServiceImpl implements UserCartService {

    @Autowired
    private UserCartRepo userCartRepo;

    @Autowired
    private UserWebClientService userWebClientService;

    @Autowired
    private ProductRestClientServiceClient productRestClientServiceClient;

   // @CircuitBreaker(name = "productService",fallbackMethod = "addToCartFallBack")
    @Override
    public boolean addUserToCart(String userId, CartDTO cartDTO) throws Exception {
        Mono<UserResponse> mono = userWebClientService.findById(userId);
        UserResponse user = mono.toFuture().get();
        if (user == null) {
            throw new Exception("user not available or issue with server");
        }
        ProductResponse product = productRestClientServiceClient.findProductById(String.valueOf(cartDTO.productId()));
        if (product == null) {
            throw new Exception("product not available or issue with server");
        }
        if (product.getStockQuantity() < cartDTO.quantity())
            return false;

        Optional<UserCart> userCartOptional = userCartRepo.findByUserIdAndProductId(userId, cartDTO.productId());
        BigDecimal newPrice = product.getPrice().multiply(BigDecimal.valueOf(cartDTO.quantity()));
        if (userCartOptional.isPresent()) {
            UserCart userCart = userCartOptional.get();
            userCart.setPrice(userCart.getPrice().add(newPrice));
            userCart.setQuantity(userCart.getQuantity() + cartDTO.quantity());
            userCartRepo.save(userCart);
        } else {
            UserCart userCart = new UserCart();
            userCart.setQuantity(cartDTO.quantity());
            userCart.setUserId(user.getId());
            userCart.setProductId(product.getProductId());
            userCart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(userCart.getQuantity())));
            userCartRepo.save(userCart);
        }
        return true;
    }

    @Override
    public void removeItems(String userId, Long id) {
        userCartRepo.deleteById(id);
    }

    @Override
    public UserCartDTO fetchCartItems(String userId, Long id) {
        return CartMapper.toCartDTOEntity(Objects.requireNonNull(userCartRepo.findById(id).orElse(null)));
    }

    @Override
    public List<UserCartDTO> fetchCartByUser(String userId) {
          //Mono<UserDTO> userOptional = userWebClientService.findById(userId);
        return userCartRepo.findByUserId(userId).stream().map(CartMapper::toCartDTOEntity).toList();
    }

//    public boolean addToCartFallBack(String userId,CartDTO cartDTO,Exception e){
//        e.printStackTrace();
//        return false;
//    }
}

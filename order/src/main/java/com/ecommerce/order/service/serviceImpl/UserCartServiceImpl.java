package com.ecommerce.order.service.serviceImpl;


import com.ecommerce.order.service.UserCartService;
import com.ecommerce.order.dto.CartDTO;
import com.ecommerce.order.dto.UserCartDTO;
import com.ecommerce.order.entity.UserCart;
import com.ecommerce.order.mapper.CartMapper;
import com.ecommerce.order.repo.UserCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserCartServiceImpl implements UserCartService {

    @Autowired
    private UserCartRepo userCartRepo;

    @Override
    public boolean addUserToCart(String userId, CartDTO cartDTO) {
        /*Optional<User> user = userRepo.findById(Long.parseLong(userId));
        if (user.isEmpty()) {
            return false;
        }
        Optional<Product> product = productRepo.findById(cartDTO.productId());
        if (product.isEmpty()) {
            return false;
        }
        if (product.get().getStockQuantity() < cartDTO.quantity())
            return false;

         */
        Long userIde = Long.parseLong(userId);
        Optional<UserCart> userCartOptional = userCartRepo.findByUserIdAndProductId(userIde, cartDTO.productId());
        //  BigDecimal newPrice = product.get().getPrice().multiply(BigDecimal.valueOf(cartDTO.quantity()));
        if (userCartOptional.isPresent()) {
            UserCart userCart = userCartOptional.get();
            //  userCart.setPrice(userCart.getPrice().add(newPrice));
            userCart.setQuantity(userCart.getQuantity() + cartDTO.quantity());
            userCartRepo.save(userCart);
        } else {
            UserCart userCart = new UserCart();
            userCart.setQuantity(cartDTO.quantity());
            //  userCart.setUser(user.get());
            //  userCart.setProduct(product.get());
            //  userCart.setPrice(product.get().getPrice().multiply(BigDecimal.valueOf(userCart.getQuantity())));
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
        //  Optional<User> userOptional = userRepo.findById(Long.parseLong(userId));
        return userCartRepo.findByUserId(Long.parseLong(userId)).stream().map(CartMapper::toCartDTOEntity).toList();
    }
}

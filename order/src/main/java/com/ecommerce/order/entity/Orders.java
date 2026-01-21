package com.ecommerce.order.entity;

import com.ecommerce.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "orders_info")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String userId;
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderedItems> orderItemsList = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime creationTimeStamp;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

}
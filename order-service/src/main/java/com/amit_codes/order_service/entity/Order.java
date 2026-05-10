package com.amit_codes.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Table(name = "orders")
@Entity
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Long quantity;
    private double total;

    public Order(Long productId, Long quantity, Double total) {
        this.total = total;
        this.quantity = quantity;
        this.productId = productId;
    }
}

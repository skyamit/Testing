package com.amit_codes.order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Table(name = "product")
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private Long stock;
    private Long quantity;
    private Long price = 10L;

    public Product(long id, String name, Long stock, Long quantity) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.quantity = quantity;
    }
}

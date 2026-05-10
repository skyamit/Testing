package com.amit_codes.order_service.client;

import com.amit_codes.order_service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductClient {

    public Product getProduct(Long productId) {
        return new Product(productId, "Book", 10L, 1L);
    }
}

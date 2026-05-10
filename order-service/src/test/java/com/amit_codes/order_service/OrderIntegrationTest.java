package com.amit_codes.order_service;

import com.amit_codes.order_service.dto.OrderRequest;
import com.amit_codes.order_service.entity.Order;
import com.amit_codes.order_service.repository.OrderRepository;
import com.amit_codes.order_service.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
class OrderIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveOrderInDatabase() {
        OrderRequest request = new OrderRequest(1L, 2L);

        Order order = orderService.placeOrder(request);

        Optional<Order> saved = orderRepository.findById(order.getId());

        assertTrue(saved.isPresent());
    }
}
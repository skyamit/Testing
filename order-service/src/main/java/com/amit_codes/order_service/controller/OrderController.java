package com.amit_codes.order_service.controller;

import com.amit_codes.order_service.dto.OrderRequest;
import com.amit_codes.order_service.entity.Order;
import com.amit_codes.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request) {
        if (request.getProductId() == null
                || request.getQuantity() == null
                || request.getQuantity() == 0L)
            return ResponseEntity.badRequest().build();
        Order order = orderService.placeOrder(request);
        return ResponseEntity.ok(order);
    }
}
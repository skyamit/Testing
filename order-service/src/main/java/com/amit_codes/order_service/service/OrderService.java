package com.amit_codes.order_service.service;

import com.amit_codes.order_service.client.PaymentClient;
import com.amit_codes.order_service.client.ProductClient;
import com.amit_codes.order_service.dto.OrderRequest;
import com.amit_codes.order_service.entity.Order;
import com.amit_codes.order_service.entity.Product;
import com.amit_codes.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;

    public OrderService(ProductClient productClient,
                        PaymentClient paymentClient,
                        OrderRepository orderRepository) {
        this.productClient = productClient;
        this.paymentClient = paymentClient;
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(OrderRequest request) {
        Product product = productClient.getProduct(request.getProductId());

        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        double total = product.getPrice() * request.getQuantity();

        Order order = new Order(request.getProductId(), request.getQuantity(), total);

        orderRepository.save(order);

        paymentClient.processPayment(order.getId(), total);

        return order;
    }
}

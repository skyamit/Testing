package com.amit_codes.order_service;

import com.amit_codes.order_service.client.PaymentClient;
import com.amit_codes.order_service.client.ProductClient;
import com.amit_codes.order_service.dto.OrderRequest;
import com.amit_codes.order_service.entity.Order;
import com.amit_codes.order_service.entity.Product;
import com.amit_codes.order_service.repository.OrderRepository;
import com.amit_codes.order_service.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private ProductClient productClient;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPlaceOrderSuccessfully() {

        Product product = new Product(1L, "Phone", 10L, 10L, 10000L);

        when(productClient.getProduct(1L)).thenReturn(product);

        OrderRequest request = new OrderRequest(1L, 2L);

        Order order = orderService.placeOrder(request);

        assertNotNull(order);
        assertEquals(20000D, order.getTotal());

        verify(orderRepository, times(1)).save(any(Order.class));
//        verify(paymentClient).processPayment(anyLong(), eq(20000));
    }

    @Test
    void shouldThrowExceptionWhenStockIsLow() {

        Product product = new Product(1L, "Phone", 1L, 1L);

        when(productClient.getProduct(1L)).thenReturn(product);

        OrderRequest request = new OrderRequest(1L, 5L);

        assertThrows(RuntimeException.class,
                () -> orderService.placeOrder(request));

        verify(orderRepository, never()).save(any());
        verify(paymentClient, never()).processPayment(anyLong(), anyDouble());
    }
}
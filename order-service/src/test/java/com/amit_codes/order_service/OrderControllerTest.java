package com.amit_codes.order_service;

import com.amit_codes.order_service.controller.OrderController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.amit_codes.order_service.dto.OrderRequest;
import com.amit_codes.order_service.entity.Order;
import com.amit_codes.order_service.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldPlaceOrderSuccessfully() throws Exception {

        OrderRequest request = new OrderRequest(1L, 2L);
        Order order = new Order(1L, 1L, 2L, 20000L);

        when(orderService.placeOrder(any(OrderRequest.class)))
                .thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(20000L))
                .andExpect(jsonPath("$.quantity").value(2L));

        verify(orderService).placeOrder(any());
    }

    @Test
    void shouldFailWhenProductIdIsNull() throws Exception {

        OrderRequest request = new OrderRequest(null, 2L);

        mockMvc.perform(post("/orders")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailWhenQuantityIsInvalid() throws Exception {

        OrderRequest request = new OrderRequest(1L, 0L);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnMultipleValidationErrors() throws Exception {

        OrderRequest request = new OrderRequest(null, 0L);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnInternalServerErrorWhenServiceFails() throws Exception {

        OrderRequest request = new OrderRequest(1L, 5L);

        when(orderService.placeOrder(any()))
                .thenThrow(new RuntimeException("Stock not available"));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldFailWhenRequestBodyIsMissing() throws Exception {

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailWhenJsonIsInvalid() throws Exception {

        String invalidJson = "{ productId: }";

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}
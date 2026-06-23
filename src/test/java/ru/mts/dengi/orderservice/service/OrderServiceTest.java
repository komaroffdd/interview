package ru.mts.dengi.orderservice.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mts.dengi.orderservice.dto.CreateOrderRequest;
import ru.mts.dengi.orderservice.dto.OrderResponse;
import ru.mts.dengi.orderservice.model.Order;
import ru.mts.dengi.orderservice.repository.OrderRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_Success() {
        // Given
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerId(1L);

        CreateOrderRequest.OrderItemRequest item = new CreateOrderRequest.OrderItemRequest();
        item.setProductName("Test Product");
        item.setQuantity(2);
        item.setPrice(10.0);

        request.setItems(Arrays.asList(item));

        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        // When
        OrderResponse response = orderService.createOrder(request);

        // Then
        assertNotNull(response);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(kafkaProducerService, times(1)).sendOrderEvent(any(Order.class));
    }

    @Test
    void getOrder_NotFound_ThrowsException() {
        // Given
        Long orderId = 999L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> orderService.getOrder(orderId));
    }
}

package ru.mts.dengi.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.dengi.orderservice.dto.CreateOrderRequest;
import ru.mts.dengi.orderservice.dto.OrderResponse;
import ru.mts.dengi.orderservice.dto.PaymentRequest;
import ru.mts.dengi.orderservice.dto.PaymentResponse;
import ru.mts.dengi.orderservice.service.OrderService;
import ru.mts.dengi.orderservice.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/order{id}/payments")
    public ResponseEntity<PaymentResponse> execute(@RequestBody PaymentRequest paymentRequest, @PathVariable Long orderId) {
        PaymentResponse paymentResponse = paymentService.execute(paymentRequest, orderId);
        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        OrderResponse response = orderService.getOrder(orderId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<OrderResponse> responses = orderService.getOrdersByCustomer(customerId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {
        OrderResponse response = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

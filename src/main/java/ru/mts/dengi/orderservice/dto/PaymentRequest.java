package ru.mts.dengi.orderservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private Long customerId;
    private PaymentMethod paymentMethod;
    private BigDecimal totalAmount;
}

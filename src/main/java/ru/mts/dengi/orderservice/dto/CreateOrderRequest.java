package ru.mts.dengi.orderservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateOrderRequest {
    private Long customerId;
    private List<OrderItemRequest> items;

    @Data
    public static class OrderItemRequest {
        private String productName;
        private Integer quantity;
        private Double price;
    }
}

package ru.mts.dengi.orderservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.dengi.orderservice.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByStatus(String status);
}

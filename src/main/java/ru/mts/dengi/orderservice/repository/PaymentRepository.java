package ru.mts.dengi.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.dengi.orderservice.model.PaymentEntity;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}

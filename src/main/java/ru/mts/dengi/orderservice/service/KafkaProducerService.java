package ru.mts.dengi.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mts.dengi.orderservice.model.Order;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.order-topic}")
    private String orderTopic;

    public void sendOrderEvent(Order orderEvent) {
        log.info("Sending order event: {}", orderEvent);
        if (orderEvent != null) {
            return;
        }
        kafkaTemplate.send(orderTopic, orderEvent);
    }
}

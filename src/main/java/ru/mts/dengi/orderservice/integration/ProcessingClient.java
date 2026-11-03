package ru.mts.dengi.orderservice.integration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessingClient {


    public ProcessingResponse doPayment(Long customerId) {
        // Запрос на выполнение списание баллов
        return new ProcessingResponse(
                "1",
                Status.IN_PROGRESS
        );
    }

    public ProcessingResponse getStatus(String paymentId) {
        // Запрос в лояльность на статус
        return new ProcessingResponse(
                "1",
                Status.SUCCESS
        );
    }


    @Data
    @AllArgsConstructor
    public static class ProcessingResponse {
        private String paymentId;
        private Status status;
    }

    public enum Status {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }
}

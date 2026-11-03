package ru.mts.dengi.orderservice.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SbpClient {

    public String getQr(Long customerId) {
        return "someQrId";
    }

    public SbpResponse doPayment(String qrId) {
        // Запрос на выполнение платежа по куару
        return new SbpResponse(
                "1",
                SbpStatus.IN_PROGRESS
        );
    }

    public SbpResponse getStatus(String paymentId) {
        // Запрос в сбп на статус
        return new SbpResponse(
                "1",
                SbpStatus.SUCCESS
        );
    }


    @Data
    @AllArgsConstructor
    public static class SbpResponse {
        private String paymentId;
        private SbpStatus sbpStatus;
    }

    public enum SbpStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }
}

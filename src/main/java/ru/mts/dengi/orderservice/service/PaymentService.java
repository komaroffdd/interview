package ru.mts.dengi.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mts.dengi.orderservice.dto.PaymentMethod;
import ru.mts.dengi.orderservice.dto.PaymentRequest;
import ru.mts.dengi.orderservice.dto.PaymentResponse;
import ru.mts.dengi.orderservice.integration.LoyaltyClient;
import ru.mts.dengi.orderservice.integration.ProcessingClient;
import ru.mts.dengi.orderservice.integration.SbpClient;
import ru.mts.dengi.orderservice.model.PaymentEntity;
import ru.mts.dengi.orderservice.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final SbpClient sbpClient;
    private final LoyaltyClient loyaltyClient;
    private final ProcessingClient processingClient;

    public PaymentResponse execute(PaymentRequest paymentRequest, Long orderId) {
        PaymentEntity paymentEntity = new PaymentEntity(
                UUID.randomUUID(),
                paymentRequest.getCustomerId(),
                "CHECK",
                orderId,
                paymentRequest.getPaymentMethod().name(),
                paymentRequest.getTotalAmount(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        paymentRepository.save(paymentEntity);

        if (paymentRequest.getPaymentMethod().equals(PaymentMethod.SBP)) {
            paymentEntity.setCurrentStatus("SUCCESS");
            paymentRepository.save(paymentEntity);
            String qr = sbpClient.getQr(paymentRequest.getCustomerId());
            SbpClient.SbpResponse sbpResponse = sbpClient.doPayment(qr);
            return new PaymentResponse(
                    sbpResponse.getPaymentId(),
                    sbpResponse.getSbpStatus().name()
            );
        } else if (paymentRequest.getPaymentMethod().equals(PaymentMethod.CARD)) {
            paymentEntity.setCurrentStatus("SUCCESS");
            paymentRepository.save(paymentEntity);
            ProcessingClient.ProcessingResponse processingResponse = processingClient.doPayment(paymentRequest.getCustomerId());
            return new PaymentResponse(
                    processingResponse.getPaymentId(),
                    processingResponse.getStatus().name()
            );
        } else {
            paymentEntity.setCurrentStatus("SUCCESS");
            paymentRepository.save(paymentEntity);
            LoyaltyClient.LoayltyResponse loayltyResponse = loyaltyClient.doPayment(paymentRequest.getCustomerId());
            return new PaymentResponse(
                    loayltyResponse.getLoyaltyId(),
                    loayltyResponse.getStatus().name()
            );
        }
    }
}

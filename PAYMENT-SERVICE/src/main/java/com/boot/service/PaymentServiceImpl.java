package com.boot.service;

import com.boot.entity.TransactionDetails;
import com.boot.modal.PaymentMode;
import com.boot.modal.PaymentRequest;
import com.boot.modal.PaymentResponse;
import com.boot.repo.ITransactionRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private ITransactionRepo transactionRepo;

    @Override
    public Long doPayment(PaymentRequest request) {
        log.info("Creating payment...");
        TransactionDetails transactionDetails=TransactionDetails.builder()
                .amount(request.getAmount())
                .orderId(request.getOrderId())
                .paymentDate(Instant.now())
                .paymentMode(request.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .referenceNumber(request.getReferenceNumber())
                .build();
        transactionRepo.save(transactionDetails);
        log.info("Transaction completed :: {}",transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {

        log.info("Getting payment details for order id:: {}",orderId);

        TransactionDetails transactionDetails = transactionRepo.findByOrderId(Long.valueOf(orderId));

        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .orderID(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .paymentDate(transactionDetails.getPaymentDate())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .build();

        return paymentResponse;
    }
}

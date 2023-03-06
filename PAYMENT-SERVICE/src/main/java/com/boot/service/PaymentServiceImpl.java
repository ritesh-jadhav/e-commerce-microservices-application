package com.boot.service;

import com.boot.entity.TransactionDetails;
import com.boot.modal.PaymentRequest;
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
}

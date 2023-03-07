package com.boot.service;

import com.boot.modal.PaymentRequest;
import com.boot.modal.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest request);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}

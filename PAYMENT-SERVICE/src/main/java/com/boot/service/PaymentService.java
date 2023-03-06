package com.boot.service;

import com.boot.modal.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest request);
}

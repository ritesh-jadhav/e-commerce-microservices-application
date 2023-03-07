package com.boot.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Long orderID;
    private Long amount;
    private Instant paymentDate;
    private String status;
    private PaymentMode paymentMode;
}

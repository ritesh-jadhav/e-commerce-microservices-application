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
public class OrderResponse {
    private Long orderId;
    private Long amount;
    private String orderStatus;
    private Instant orderDate;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductDetails {
        private Long product_id;
        private String name;
        private  Long quantity;
        private Long price;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentDetails {
        private Long paymentId;
        private String paymentStatus;
        private  Instant paymentDate;
        private PaymentMode paymentMode;
    }

}

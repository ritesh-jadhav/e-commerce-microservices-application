package com.boot.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long product_id;
    private Long quantity;
    private Long amount;
    private  PaymentMode paymentMode;

}

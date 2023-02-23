package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long order_id;
    @Column(name = "PRODUCT_ID")
    private Long product_id;
    @Column(name = "ORDER_DATE_AND_TIME")
    private Instant orderDate;
    @Column(name = "QTY")
    private Long quantity;
    @Column(name = "PAYABLE_AMOUNT")
    private Long totalAmount;
    @Column(name = "STATUS")
    private String status;
}

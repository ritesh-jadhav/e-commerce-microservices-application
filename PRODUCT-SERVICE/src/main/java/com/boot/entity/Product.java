package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private Long product_id;
    @Column(name = "PRODUCT_NAME")
    private String name;
    @Column(name = "PRODUCT_QTY")
    private Long quantity;
    @Column(name = "PRODUCT_PRICE")
    private Long price;
}

package com.boot.modal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String name;
    private Long quantity;
    private Long price;
}

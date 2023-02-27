package com.boot.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/product")
public interface IProductService {
    @PutMapping("/{product_id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("product_id") Long product_id,
                                               @RequestParam Long quantity
    );
}

package com.boot.service;

import com.boot.entity.Product;
import com.boot.modal.ProductRequest;
import com.boot.modal.ProductResponse;

public interface IProductOperationService {
    Long saveProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long product_id);

    void ReduceQuantity(Long product_id,Long quantity);
}

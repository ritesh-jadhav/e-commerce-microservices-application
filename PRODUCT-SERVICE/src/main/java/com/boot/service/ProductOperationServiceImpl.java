package com.boot.service;

import com.boot.entity.Product;
import com.boot.exception.ProductServiceCustomException;
import com.boot.modal.ProductRequest;
import com.boot.modal.ProductResponse;
import com.boot.repo.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Slf4j
public class ProductOperationServiceImpl implements IProductOperationService{

    @Autowired
   private IProductRepository productRepo;

    @Override
    public Long saveProduct(ProductRequest productRequest) {
      log.info("saving product...");
      Product product = Product.builder()
              .quantity(productRequest.getQuantity())
              .name(productRequest.getName())
              .price(productRequest.getPrice())
              .build();
        Product savedProduct = productRepo.save(product);
        log.info("Product Saved successfully !");
        return savedProduct.getProduct_id();
    }

    @Override
    public ProductResponse getProductById(Long product_id) {
        log.info("Fetching Product....");
        Product requiredProduct = productRepo.findById(product_id).orElseThrow(() -> new ProductServiceCustomException("Product not found with given id","PRODUCT_NOT_FOUND"));
        ProductResponse response = ProductResponse.builder()
                .product_id(requiredProduct.getProduct_id())
                .name(requiredProduct.getName())
                .price(requiredProduct.getPrice())
                .quantity(requiredProduct.getQuantity())
                .build();
        return response;
    }

    @Override
    public void ReduceQuantity( Long product_id,Long quantity) {
        Product requiredProduct = productRepo.findById(product_id).orElseThrow(() -> new ProductServiceCustomException("Product not found with given id","PRODUCT_NOT_FOUND"));
        if (requiredProduct.getQuantity() < quantity){
            throw  new ProductServiceCustomException("Product does not have sufficient quantity","INSUFFICIENT_QUANTITY");
        }
        requiredProduct.setQuantity(requiredProduct.getQuantity() - quantity);
        productRepo.save(requiredProduct);
        log.info("Quantity reduced Successfully...!");
    }
}

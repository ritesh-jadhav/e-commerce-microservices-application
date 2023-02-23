package com.boot.controller;

import com.boot.modal.ProductRequest;
import com.boot.modal.ProductResponse;
import com.boot.service.IProductOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductOperationService productService;

    @PostMapping
    public ResponseEntity<Long> saveProduct(@RequestBody ProductRequest request){
        Long response = productService.saveProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> fetchProduct(@PathVariable("product_id") Long product_id){

          ProductResponse response =  productService.getProductById(product_id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}

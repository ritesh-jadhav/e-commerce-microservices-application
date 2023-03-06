package com.boot.controller;

import com.boot.modal.OrderRequest;
import com.boot.service.IorderOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderOperationController {

    @Autowired
    IorderOperationService OrderService;

    @PostMapping
    public ResponseEntity<Long> saveOrder(@RequestBody OrderRequest request){
        Long response = OrderService.saveOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

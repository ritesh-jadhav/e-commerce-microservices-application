package com.boot.controller;

import com.boot.modal.OrderRequest;
import com.boot.modal.OrderResponse;
import com.boot.service.IOrderOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderOperationController {

    @Autowired
    IOrderOperationService OrderService;

    @PostMapping
    public ResponseEntity<Long> saveOrder(@RequestBody OrderRequest request){
        Long response = OrderService.saveOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("id") Long orderId){
        OrderResponse orderResponse = OrderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }

}

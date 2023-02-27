package com.boot.service;

import com.boot.entity.Order;
import com.boot.external.client.IProductService;
import com.boot.modal.OrderRequest;
import com.boot.repo.IorderRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class OrderOperationServiceImpl implements IorderOperationService{

    //    Call product service
    //    Block the product -> Reduce Quantity
    //    save the order

    @Autowired
    private IorderRepository orderRepo;

    @Autowired
    private IProductService productService;

    @Override
    public Long saveOrder(OrderRequest request) {
        log.info("creating order...");

        productService.reduceQuantity(request.getProduct_id(), request.getQuantity());
        log.info("Creating order with status CREATED");

        Order createOrder = Order.builder()
                .orderDate(Instant.now())
                .product_id(request.getProduct_id())
                .quantity(request.getQuantity())
                .totalAmount(request.getAmount())
                .status("CREATED")
                .build();
        orderRepo.save(createOrder);

        log.info("Order Created...!");

        return createOrder.getOrder_id();
    }
}

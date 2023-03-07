package com.boot.service;

import com.boot.entity.Order;
import com.boot.exception.CustomException;
import com.boot.external.client.IPaymentService;
import com.boot.external.client.IProductService;
import com.boot.external.request.PaymentRequest;
import com.boot.external.response.PaymentResponse;
import com.boot.modal.OrderRequest;
import com.boot.modal.OrderResponse;
import com.boot.modal.ProductResponse;
import com.boot.repo.IorderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Id;
import java.time.Instant;

@Service
@Slf4j
public class OrderOperationServiceImpl implements IOrderOperationService {

    //    Call product service
    //    Block the product -> Reduce Quantity
    //    save the order

    @Autowired
    private IorderRepository orderRepo;

    @Autowired
    private IProductService productService;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;

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
        log.info("Calling payment service...");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(createOrder.getOrder_id())
                .paymentMode(request.getPaymentMode())
                .amount(request.getAmount())
                .build();

        String orderStatus =null;

        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment Successful...!Changing order status to placed");
            orderStatus="PLACED";
        }catch (Exception exception){
            log.error("Error occurred in payment...!Changing order status to cancelled");
            orderStatus="PAYMENT_FAILED";
        }
        createOrder.setStatus(orderStatus);
        orderRepo.save(createOrder);
        log.info("Order Created...!");
        return createOrder.getOrder_id();
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {
        Order requiredOrder = orderRepo.findById(orderId).orElseThrow( () -> new CustomException("Order not found with id "+orderId,
                "ORDER_NOT_FOUND"
                ,404));
        log.info("Invoking product service for getting product details for the id:: {}",requiredOrder.getOrder_id());
        ProductResponse productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+requiredOrder.getProduct_id(), ProductResponse.class);

        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .product_id(productResponse.getProduct_id())
                .name(productResponse.getName())
                .price(productResponse.getPrice())
                .quantity(productResponse.getQuantity())
                .build();

        log.info("Calling payment service from product service to fetch payment details for orderId:: {}",requiredOrder.getOrder_id());

        PaymentResponse paymentResponse =restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+requiredOrder.getOrder_id(),PaymentResponse.class);

        OrderResponse.PaymentDetails paymentDetails= OrderResponse.PaymentDetails.builder()
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentId(paymentResponse.getPaymentId())
                .paymentMode(paymentResponse.getPaymentMode())
                .paymentStatus(paymentResponse.getStatus())
                .build();

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(requiredOrder.getOrder_id())
                .amount(requiredOrder.getTotalAmount())
                .orderDate(requiredOrder.getOrderDate())
                .orderStatus(requiredOrder.getStatus())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                 .build();
        return orderResponse;
    }
}

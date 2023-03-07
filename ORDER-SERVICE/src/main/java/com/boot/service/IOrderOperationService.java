package com.boot.service;

import com.boot.modal.OrderRequest;
import com.boot.modal.OrderResponse;

public interface IOrderOperationService {

    Long saveOrder(OrderRequest request);

    OrderResponse getOrderDetails(Long orderId);
}

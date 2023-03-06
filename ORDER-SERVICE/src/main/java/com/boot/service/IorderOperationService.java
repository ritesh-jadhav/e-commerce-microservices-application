package com.boot.service;

import com.boot.modal.OrderRequest;

public interface IorderOperationService {

    Long saveOrder(OrderRequest request);
}

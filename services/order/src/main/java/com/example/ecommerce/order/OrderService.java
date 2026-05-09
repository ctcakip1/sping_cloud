package com.example.ecommerce.order;

import org.springframework.stereotype.Service;

import com.example.ecommerce.order.dto.req.OrderRequest;

@Service
public interface OrderService {
    Integer createOrder(OrderRequest request);
}

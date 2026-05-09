package com.example.ecommerce.order.dto;

import org.springframework.stereotype.Service;

import com.example.ecommerce.order.dto.req.OrderRequest;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymantMethod(request.paymantMethod())
                .build();
    }
}

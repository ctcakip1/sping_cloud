package com.example.ecommerce.order.dto;

import org.springframework.stereotype.Service;

import com.example.ecommerce.order.dto.req.OrderRequest;
import com.example.ecommerce.order.dto.res.OrderResponse;

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

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymantMethod(),
                order.getCustomerId());
    }
}

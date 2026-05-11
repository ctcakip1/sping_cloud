package com.example.ecommerce.orderline.dto;

import org.springframework.stereotype.Service;

import com.example.ecommerce.order.dto.Order;
import com.example.ecommerce.orderline.dto.req.OrderLineRequest;
import com.example.ecommerce.orderline.dto.res.OrderLineResponse;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .order(Order.builder().id(request.orderId()).build())
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(), orderLine.getQuantity());
    }
}

package com.example.ecommerce.orderline;

import org.springframework.stereotype.Service;

import com.example.ecommerce.orderline.dto.req.OrderLineRequest;

@Service
public interface OrderLineService {
    Integer saveOrderLine(OrderLineRequest orderLineRequest);
}

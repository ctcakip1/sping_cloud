package com.example.ecommerce.orderline.impl;

import org.springframework.stereotype.Service;

import com.example.ecommerce.orderline.OrderLineRepository;
import com.example.ecommerce.orderline.OrderLineService;
import com.example.ecommerce.orderline.dto.OrderLineMapper;
import com.example.ecommerce.orderline.dto.req.OrderLineRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = orderLineMapper.toOrderLine(request);
        return orderLineRepository.save(order).getId();
    }
}

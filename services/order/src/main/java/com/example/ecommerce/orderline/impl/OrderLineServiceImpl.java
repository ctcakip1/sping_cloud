package com.example.ecommerce.orderline.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ecommerce.orderline.OrderLineRepository;
import com.example.ecommerce.orderline.OrderLineService;
import com.example.ecommerce.orderline.dto.OrderLineMapper;
import com.example.ecommerce.orderline.dto.req.OrderLineRequest;
import com.example.ecommerce.orderline.dto.res.OrderLineResponse;

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

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}

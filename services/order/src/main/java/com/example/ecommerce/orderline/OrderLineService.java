package com.example.ecommerce.orderline;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.orderline.dto.OrderLine;
import com.example.ecommerce.orderline.dto.req.OrderLineRequest;
import com.example.ecommerce.orderline.dto.res.OrderLineResponse;

@Service
public interface OrderLineService {
    Integer saveOrderLine(OrderLineRequest orderLineRequest);

    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}

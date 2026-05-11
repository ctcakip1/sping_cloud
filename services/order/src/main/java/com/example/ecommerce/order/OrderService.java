package com.example.ecommerce.order;

import java.util.List;

import com.example.ecommerce.order.dto.req.OrderRequest;
import com.example.ecommerce.order.dto.res.OrderResponse;

public interface OrderService {
    Integer createOrder(OrderRequest request);

    List<OrderResponse> findAll();

    OrderResponse findById(Integer id);
}

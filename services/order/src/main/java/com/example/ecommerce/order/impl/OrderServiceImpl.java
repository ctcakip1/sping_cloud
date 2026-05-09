package com.example.ecommerce.order.impl;

import org.springframework.stereotype.Service;

import com.example.ecommerce.customer.CustomerClient;
import com.example.ecommerce.exception.BusinessException;
import com.example.ecommerce.order.OrderRepository;
import com.example.ecommerce.order.OrderService;
import com.example.ecommerce.order.dto.OrderMapper;
import com.example.ecommerce.order.dto.req.OrderRequest;
import com.example.ecommerce.orderline.OrderLineService;
import com.example.ecommerce.orderline.dto.req.OrderLineRequest;
import com.example.ecommerce.product.ProductClient;
import com.example.ecommerce.product.PurchaseRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderLineMapper;
    private final OrderLineService orderLineService;

    @Override
    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.findById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));
        this.productClient.purchaseProducts(request.products());
        var order = this.orderRepository.save(orderLineMapper.toOrder(request));
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }
        return order.getId();
    }

}

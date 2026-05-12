package com.example.ecommerce.order.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ecommerce.customer.CustomerClient;
import com.example.ecommerce.exception.BusinessException;
import com.example.ecommerce.kafka.OrderConfirmation;
import com.example.ecommerce.kafka.OrderProducer;
import com.example.ecommerce.order.OrderRepository;
import com.example.ecommerce.order.OrderService;
import com.example.ecommerce.order.dto.OrderMapper;
import com.example.ecommerce.order.dto.req.OrderRequest;
import com.example.ecommerce.order.dto.res.OrderResponse;
import com.example.ecommerce.orderline.OrderLineService;
import com.example.ecommerce.orderline.dto.req.OrderLineRequest;
import com.example.ecommerce.payment.PaymentClient;
import com.example.ecommerce.payment.PaymentRequest;
import com.example.ecommerce.product.ProductClient;
import com.example.ecommerce.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Override
    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.findById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        var order = this.orderRepository.save(orderMapper.toOrder(request));
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }

        paymentClient.requestOrderPayment(new PaymentRequest(
                request.amount(),
                request.paymantMethod(),
                order.getId(),
                request.reference(),
                customer));

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymantMethod(),
                customer,
                purchasedProducts));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }
}

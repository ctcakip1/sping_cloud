package com.example.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.example.ecommerce.customer.CustomerResponse;
import com.example.ecommerce.order.PaymantMethod;
import com.example.ecommerce.product.PurchaseResponse;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymantMethod paymantMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products) {

}

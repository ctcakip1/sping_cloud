package com.example.ecommerce.payment;

import java.math.BigDecimal;

import com.example.ecommerce.customer.CustomerResponse;
import com.example.ecommerce.order.PaymantMethod;

public record PaymentRequest(
        BigDecimal amount,
        PaymantMethod paymantMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer) {

}

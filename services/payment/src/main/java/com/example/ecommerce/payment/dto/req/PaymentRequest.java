package com.example.ecommerce.payment.dto.req;

import java.math.BigDecimal;

import com.example.ecommerce.customer.Customer;
import com.example.ecommerce.payment.enums.PaymentMethod;

public record PaymentRequest(
                Integer id,
                BigDecimal amount,
                PaymentMethod paymentMethod,
                Integer orderId,
                String orderReference,
                Customer customer) {

}

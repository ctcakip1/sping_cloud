package com.example.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.example.ecommerce.kafka.payment.PaymentMethod;

public record OrderConfirmation(
                String orderReference,
                BigDecimal amount,
                PaymentMethod paymantMethod,
                Customer customer,
                List<Product> products) {

}

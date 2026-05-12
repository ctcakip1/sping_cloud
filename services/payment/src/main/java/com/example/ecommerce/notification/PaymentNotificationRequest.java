package com.example.ecommerce.notification;

import java.math.BigDecimal;

import com.example.ecommerce.payment.enums.PaymentMethod;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail) {

}

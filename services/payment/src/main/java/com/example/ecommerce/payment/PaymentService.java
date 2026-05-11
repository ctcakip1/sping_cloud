package com.example.ecommerce.payment;

import com.example.ecommerce.payment.dto.req.PaymentRequest;

public interface PaymentService {
    Integer create(PaymentRequest request);
}

package com.example.ecommerce.order.dto.res;

import java.math.BigDecimal;

import com.example.ecommerce.order.PaymantMethod;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymantMethod paymantMethod,
        String customerId) {

}

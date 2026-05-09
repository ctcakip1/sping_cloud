package com.example.ecommerce.order.dto.req;

import java.math.BigDecimal;
import java.util.List;

import com.example.ecommerce.order.PaymantMethod;
import com.example.ecommerce.product.PurchaseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "order amount should be positive") BigDecimal amount,
        @NotNull(message = "paymant method should be provided") PaymantMethod paymantMethod,
        @NotNull(message = "customer id should be provided") @NotEmpty(message = "customer id should not be empty") @NotBlank(message = "customer id should not be blank") String customerId,
        @NotEmpty(message = "order must contain at least one product") List<PurchaseRequest> products) {

}

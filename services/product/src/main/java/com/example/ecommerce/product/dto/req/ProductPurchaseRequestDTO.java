package com.example.ecommerce.product.dto.req;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequestDTO(
        @NotNull(message = "Product ID is required") Integer productId,
        @NotNull(message = "Quantity is required") double quantity) {

}

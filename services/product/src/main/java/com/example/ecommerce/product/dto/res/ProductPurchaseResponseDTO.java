package com.example.ecommerce.product.dto.res;

import java.math.BigDecimal;

public record ProductPurchaseResponseDTO(
        Integer productId,
        String name,
        String description,
        double quantity,
        BigDecimal price) {

}

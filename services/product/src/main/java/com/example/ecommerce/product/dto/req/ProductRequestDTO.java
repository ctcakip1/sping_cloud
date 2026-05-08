package com.example.ecommerce.product.dto.req;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequestDTO(
                Integer id,
                @NotNull(message = "Product name is required") String name,
                @NotNull(message = "Product description is required") String description,
                @Positive(message = "Product available quantity is required") double availableQuantity,
                @Positive(message = "Product price is required") BigDecimal price,
                @NotNull(message = "Category ID is required") Integer categoryId) {

}
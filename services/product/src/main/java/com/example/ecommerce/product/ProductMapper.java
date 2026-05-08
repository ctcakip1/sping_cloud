package com.example.ecommerce.product;

import org.springframework.stereotype.Service;

import com.example.ecommerce.category.Category;
import com.example.ecommerce.product.dto.Product;
import com.example.ecommerce.product.dto.req.ProductRequestDTO;
import com.example.ecommerce.product.dto.res.ProductPurchaseResponseDTO;
import com.example.ecommerce.product.dto.res.ProductResponseDTO;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequestDTO request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(Category.builder().id(request.categoryId()).build())
                .build();
    }

    public ProductResponseDTO toProductResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription());
    }

    public ProductPurchaseResponseDTO toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                quantity,
                product.getPrice());
    }
}

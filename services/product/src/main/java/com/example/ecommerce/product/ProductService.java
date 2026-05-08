package com.example.ecommerce.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.product.dto.req.ProductPurchaseRequestDTO;
import com.example.ecommerce.product.dto.req.ProductRequestDTO;
import com.example.ecommerce.product.dto.res.ProductPurchaseResponseDTO;
import com.example.ecommerce.product.dto.res.ProductResponseDTO;

@Service
public interface ProductService {
    Integer create(ProductRequestDTO request);

    List<ProductPurchaseResponseDTO> purchaseProducts(List<ProductPurchaseRequestDTO> requests);

    ProductResponseDTO findById(Integer productId);

    List<ProductResponseDTO> findAll();
}

package com.example.ecommerce.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.product.dto.Product;
import com.example.ecommerce.product.dto.req.ProductPurchaseRequestDTO;
import com.example.ecommerce.product.dto.req.ProductRequestDTO;
import com.example.ecommerce.product.dto.res.ProductPurchaseResponseDTO;
import com.example.ecommerce.product.dto.res.ProductResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@Valid @RequestBody ProductRequestDTO request) {
        return ResponseEntity.ok(productService.create(request));
    }

    @PostMapping("purchase")
    public ResponseEntity<List<ProductPurchaseResponseDTO>> purchaseProducts(
            @Valid @RequestBody List<ProductPurchaseRequestDTO> requests) {
        return ResponseEntity.ok(productService.purchaseProducts(requests));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

}

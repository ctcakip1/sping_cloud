package com.example.ecommerce.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.ecommerce.product.ProductMapper;
import com.example.ecommerce.product.ProductRepository;
import com.example.ecommerce.product.ProductService;
import com.example.ecommerce.product.dto.Product;
import com.example.ecommerce.product.dto.req.ProductPurchaseRequestDTO;
import com.example.ecommerce.product.dto.req.ProductRequestDTO;
import com.example.ecommerce.product.dto.res.ProductPurchaseResponseDTO;
import com.example.ecommerce.product.dto.res.ProductResponseDTO;
import com.example.ecommerce.product.exception.ProductPurchaseException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer create(ProductRequestDTO request) {
        var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    // public List<ProductPurchaseResponseDTO>
    // purchaseProducts(List<ProductPurchaseRequestDTO> requests) {
    // var productIds =
    // requests.stream().map(ProductPurchaseRequestDTO::productId).toList();
    // var storedProducts = productRepository.findAllByIdInOrderById(productIds);
    // if (productIds.size() != storedProducts.size()) {
    // throw new ProductPurchaseException("one or more products does not exist");
    // }

    // var storesRequest =
    // requests.stream().sorted(Comparator.comparing(ProductPurchaseRequestDTO::productId))
    // .toList();
    // var purchaseProducts = new ArrayList<ProductPurchaseResponseDTO>();
    // for (int i = 0; i < storedProducts.size(); i++) {
    // var product = storedProducts.get(i);
    // var productRequest = storesRequest.get(i);
    // if (product.getAvailableQuantity() < productRequest.quantity()) {
    // throw new ProductPurchaseException("Product " + product.getName() + " is not
    // available");
    // }
    // var newAvailableQuantity = product.getAvailableQuantity() -
    // productRequest.quantity();
    // product.setAvailableQuantity(newAvailableQuantity);
    // productRepository.save(product);
    // purchaseProducts.add(productMapper.toProductPurchaseResponse(product,
    // productRequest.quantity()));
    // }
    // return purchaseProducts;
    // }
    public List<ProductPurchaseResponseDTO> purchaseProducts(List<ProductPurchaseRequestDTO> requests) {

        Map<Integer, ProductPurchaseRequestDTO> requestMap = requests.stream()
                .collect(Collectors.toMap(ProductPurchaseRequestDTO::productId, request -> request));
        List<Integer> productIds = new ArrayList<>(requestMap.keySet());
        List<Product> storedProducts = productRepository.findAllByIdIn(productIds);
        if (productIds.size() > storedProducts.size()) {
            throw new ProductPurchaseException("one or more products does not exist");
        }
        List<ProductPurchaseResponseDTO> purchaseProducts = new ArrayList<>();
        for (Product product : storedProducts) {
            var request = requestMap.get(product.getId());
            if (request.quantity() > product.getAvailableQuantity()) {
                throw new ProductPurchaseException("Product " + product.getName() + " is not available");
            }
            product.setAvailableQuantity(product.getAvailableQuantity() - request.quantity());
            productRepository.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product, request.quantity()));
        }
        return purchaseProducts;
    }

    public ProductResponseDTO findById(Integer productId) {
        return productRepository.findById(productId).map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }
}

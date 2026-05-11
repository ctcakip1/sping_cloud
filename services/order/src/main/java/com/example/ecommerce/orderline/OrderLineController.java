package com.example.ecommerce.orderline;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.orderline.dto.res.OrderLineResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService orderLineService;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderLineResponse>> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(id));
    }
}

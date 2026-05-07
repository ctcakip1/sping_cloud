package com.alibou.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CustomerRequest req){
        return ResponseEntity.ok(customerService.createCustomer(req));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid CustomerRequest req){
        customerService.updateCustomer(req);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> fetchAll(){
        return ResponseEntity.ok(customerService.fetchAllCustomer());
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("id") String id){
        return  ResponseEntity.ok(customerService.existsById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("id") String id){
        return  ResponseEntity.ok(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
        return  ResponseEntity.accepted().build();
    }

}

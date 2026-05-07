package com.alibou.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest req) {
        if(req == null){
            return null;
        }
        return Customer.builder()
                .id(req.id())
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .address(req.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse((customer.getId()), customer.getFirstName()
                , customer.getLastName(), customer.getEmail(), customer.getAddress());
    }
}

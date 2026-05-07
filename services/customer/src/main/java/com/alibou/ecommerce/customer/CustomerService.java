package com.alibou.ecommerce.customer;

import ch.qos.logback.core.util.StringUtil;
import com.alibou.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;


    public String createCustomer(CustomerRequest req) {
        var customer = customerRepository.save(mapper.toCustomer(req));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest req) {
        var customer = customerRepository.findById(req.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("khong the update nguoi dung:: khong co nguoi dung co id = %s", req.id())));
        mergerCustomer(customer, req);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest req) {
        if(StringUtils.isNotBlank(req.firstName())){
            customer.setFirstName(req.firstName());
        }
        if(StringUtils.isNotBlank(req.lastName())){
            customer.setLastName(req.lastName());
        }
        if(StringUtils.isNotBlank(req.email())){
            customer.setEmail(req.email());
        }
        if(req.address() != null){
            customer.setAddress(req.address());
        }
    }


    public List<CustomerResponse> fetchAllCustomer() {
        return customerRepository.findAll().stream().map(mapper :: fromCustomer).collect(Collectors.toList());
    }

    public Boolean existsById(String id) {
        return customerRepository.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {
        return customerRepository.findById(id).map(mapper :: fromCustomer).orElseThrow(() -> new CustomerNotFoundException(
                format("khong the update nguoi dung:: khong co nguoi dung co id = %s", id)));
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}

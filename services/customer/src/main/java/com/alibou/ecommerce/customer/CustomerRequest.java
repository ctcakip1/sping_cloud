package com.alibou.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(String id,
                              @NotNull(message = "firstName khong trong")
                               String firstName,
                              @NotNull(message = "lastName khong trong")
                              String lastName,
                              @Email(message = "email khong trong")
                              String email,
                              Address address) {

}

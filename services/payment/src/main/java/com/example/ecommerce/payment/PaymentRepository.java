package com.example.ecommerce.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.payment.dto.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}

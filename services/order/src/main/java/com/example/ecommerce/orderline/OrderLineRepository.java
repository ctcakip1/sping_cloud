package com.example.ecommerce.orderline;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.orderline.dto.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

}


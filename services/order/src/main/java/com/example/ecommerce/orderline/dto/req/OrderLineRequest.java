package com.example.ecommerce.orderline.dto.req;

public record OrderLineRequest(Integer id, Integer orderId, Integer productId, double quantity) {

}

package com.example.ecommerce.payment.impl;

import org.springframework.stereotype.Service;

import com.example.ecommerce.notification.NotificationProducer;
import com.example.ecommerce.notification.PaymentNotificationRequest;
import com.example.ecommerce.payment.PaymentRepository;
import com.example.ecommerce.payment.PaymentService;
import com.example.ecommerce.payment.dto.PaymentMapper;
import com.example.ecommerce.payment.dto.req.PaymentRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Integer create(PaymentRequest request) {
        var payment = paymentMapper.toPayment(request);
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()));
        return paymentRepository.save(payment).getId();
    }

}

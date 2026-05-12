package com.example.ecommerce.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.ecommerce.email.EmailService;
import com.example.ecommerce.kafka.order.OrderConfirmation;
import com.example.ecommerce.kafka.payment.PaymentConfirmation;
import com.example.ecommerce.notification.NotificationRepository;
import com.example.ecommerce.notification.dto.Notification;
import com.example.ecommerce.notification.enums.NotificationType;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation notification) throws MessagingException {
        log.info("Processing order confirmation: {}", notification);
        notificationRepository.save(
                Notification.builder().type(NotificationType.ORDER_CONFIRMATION).notificationDate(LocalDateTime.now())
                        .orderConfirmation(notification).build());

        var customerName = notification.customer().firstname() + " " + notification.customer().lastname();
        emailService.sendOrderConfirmationEmail(notification.customer().email(), customerName,
                notification.totalAmount(), notification.orderReference(), notification.products());
    };

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentConfirmationNotification(PaymentConfirmation notification) throws MessagingException {
        log.info("Processing payment confirmation: {}", notification);
        notificationRepository.save(
                Notification.builder().type(NotificationType.PAYMENT_CONFIRMATION).notificationDate(LocalDateTime.now())
                        .paymentConfirmation(notification).build());

        var customerName = notification.customerFirstName() + " " + notification.customerLastName();
        emailService.sendPaymentSuccessEmail(notification.customerEmail(), customerName, notification.amount(),
                notification.orderReference());
    }

}

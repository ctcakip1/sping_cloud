package com.example.ecommerce.notification;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.notification.dto.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}

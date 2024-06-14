package com.ahuynh.notification_service.model;

import lombok.Data;

import java.time.Instant;
@Data
public class NotificationRequest {
        private String title;
        private String body;
        private String topic;
        private String token;
}

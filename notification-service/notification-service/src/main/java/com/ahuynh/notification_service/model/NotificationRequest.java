package com.ahuynh.notification_service.model;

import java.time.Instant;

public class NotificationRequest {
        private Long userId;
        private String content;
        private boolean status = false;
        private Instant createdAt = Instant.now();
}

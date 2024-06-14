package com.ahuynh.notification_service.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    LocalDateTime timestamp;
    String message;
    String details;

    public ErrorDetails(LocalDateTime now, String message, String localizedMessage) {
    }
}
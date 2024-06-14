package com.ahuynh.core_service.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRequest {
    private String title;
    private String body;
    private String topic;
    private String token;
}


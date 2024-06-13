package com.ahuynh.notification_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private Long id;
    private Long userId;
    private String content;
    private boolean status = false;
    private Instant createdAt = Instant.now();

    public static NotificationResponse toResponse(NotificationEntity notification) {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setId(notification.getId());
        notificationResponse.setUserId(notification.getUserId());
        notificationResponse.setContent(notification.getContent());
        notificationResponse.setStatus(notification.isStatus());
        notificationResponse.setCreatedAt(notification.getCreatedAt());
        return notificationResponse;

    }


    public static List<NotificationResponse> toResponseList(List<NotificationEntity> notification) {
        return notification.stream()
                .map(NotificationResponse::toResponse)
                .collect(Collectors.toList());


    }
}

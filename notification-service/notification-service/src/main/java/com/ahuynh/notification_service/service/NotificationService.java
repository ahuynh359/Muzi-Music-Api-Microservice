package com.ahuynh.notification_service.service;

import com.ahuynh.notification_service.model.NotificationEntity;
import com.ahuynh.notification_service.model.NotificationRequest;
import com.ahuynh.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationEntity createNotification(NotificationRequest notificationRequest) {
        NotificationEntity notificationEntity = new NotificationEntity();

        return notificationRepository.save(notificationEntity);
    }
}

package com.ahuynh.notification_service.controller;

import com.ahuynh.notification_service.model.NotificationResponse;
import com.ahuynh.notification_service.repository.NotificationRepository;
import com.ahuynh.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

   
}

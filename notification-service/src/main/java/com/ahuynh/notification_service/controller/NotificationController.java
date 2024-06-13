package com.ahuynh.notification_service.controller;

import com.ahuynh.notification_service.model.ApiResponse;
import com.ahuynh.notification_service.model.NotificationRequest;
import com.ahuynh.notification_service.model.NotificationResponse;
import com.ahuynh.notification_service.repository.NotificationRepository;
import com.ahuynh.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

   @PostMapping("/create")
    public  ResponseEntity<?> createNotification(@RequestBody NotificationRequest notificationRequest) {
       return new ResponseEntity<>(new ApiResponse("Sign in success",notificationService.createNotification(notificationRequest)), HttpStatus.OK);

   }

}

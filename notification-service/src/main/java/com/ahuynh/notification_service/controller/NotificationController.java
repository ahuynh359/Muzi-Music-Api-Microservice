package com.ahuynh.notification_service.controller;

import com.ahuynh.notification_service.model.MessageResponse;
import com.ahuynh.notification_service.model.NotificationRequest;
import com.ahuynh.notification_service.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/noti")
@RequiredArgsConstructor
public class NotificationController {

    private final FCMService fcmService;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest request) throws ExecutionException, InterruptedException, ExecutionException {
        fcmService.sendMessageToToken(request);
        return new ResponseEntity<>(new MessageResponse( "Notification has been sent."), HttpStatus.OK);
    }
}
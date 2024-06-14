package com.ahuynh.core_service.service;

import com.ahuynh.core_service.configuration.CustomFeignClientConfiguration;
import com.ahuynh.core_service.model.rest.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "muzi-music-user",
        configuration = CustomFeignClientConfiguration.class,url = "http://localhost:8765/notification-service")
public interface FeignClientNotification {

    @GetMapping("/noti/send")
    void sendNotification(@RequestParam NotificationRequest request);


}
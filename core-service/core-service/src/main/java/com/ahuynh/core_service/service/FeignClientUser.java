package com.ahuynh.core_service.service;

import com.ahuynh.core_service.configuration.CustomFeignClientConfiguration;
import com.ahuynh.core_service.model.rest.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "muzi-music-client",
        configuration = CustomFeignClientConfiguration.class,url = "http://localhost:8765/user-service")
public interface FeignClientUser {

    @GetMapping("/user/information/1")
    UserResponse getUser();

    @GetMapping("/auth")
    String test();

}
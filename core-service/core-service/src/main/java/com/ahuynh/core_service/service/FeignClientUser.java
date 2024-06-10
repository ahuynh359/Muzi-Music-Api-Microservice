package com.ahuynh.core_service.service;

import com.ahuynh.core_service.configuration.CustomFeignClientConfiguration;
import com.ahuynh.core_service.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "muzi-music-client",
        configuration = CustomFeignClientConfiguration.class,url = "http://localhost:8765/user-service")
public interface FeignClientUser {

    @GetMapping("/user/information/1")
    UserDto getUser();

    @GetMapping("/auth")
    String test();

}
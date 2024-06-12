package com.ahuynh.core_service.service;

import com.ahuynh.core_service.configuration.CustomFeignClientConfiguration;
import com.ahuynh.core_service.model.rest.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "muzi-music-client",
        configuration = CustomFeignClientConfiguration.class,url = "http://localhost:8765/user-service")
public interface FeignClientUser {

    @GetMapping("/user/information/{id}")
    UserResponse getUser(@PathVariable Long id);

    @GetMapping("/user/search/{keyword}")
    List<UserResponse> searchUser(@PathVariable String keyword);

    @GetMapping("/auth")
    String test();

}
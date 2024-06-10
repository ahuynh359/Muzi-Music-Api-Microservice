package com.ahuynh.user_service.model.rest.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userNameOrEmail;
    private String password;
}

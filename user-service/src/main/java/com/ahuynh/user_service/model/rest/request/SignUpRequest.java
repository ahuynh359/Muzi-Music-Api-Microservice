package com.ahuynh.user_service.model.rest.request;

import lombok.Data;

@Data
public class SignUpRequest {

    private String email;
    private String password;
    private String username;


}
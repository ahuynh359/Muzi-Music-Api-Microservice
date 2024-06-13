package com.ahuynh.user_service.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

@Data
@AllArgsConstructor
public class ChangePasswordRequest
{
    private Long userId;
    private String oldPassword;
    private String newPassword;
}

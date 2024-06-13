package com.ahuynh.user_service.model.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeDeviceTokenRequest {
    private Long userId;
    @NotBlank
    private String deviceToken;
}

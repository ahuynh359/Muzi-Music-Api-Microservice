package com.ahuynh.user_service.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String jwt;
}

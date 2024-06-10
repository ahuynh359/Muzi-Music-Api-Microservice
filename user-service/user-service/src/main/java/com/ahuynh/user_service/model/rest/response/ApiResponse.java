package com.ahuynh.user_service.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse implements Serializable {

    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }


}

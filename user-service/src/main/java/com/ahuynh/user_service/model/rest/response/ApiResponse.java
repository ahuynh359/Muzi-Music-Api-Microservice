package com.ahuynh.user_service.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApiResponse implements Serializable {

    private String message;
    private Object data;



}

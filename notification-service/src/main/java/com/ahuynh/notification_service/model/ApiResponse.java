package com.ahuynh.notification_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApiResponse implements Serializable {

    private String message;
    private Object data;



}


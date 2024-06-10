package com.ahuynh.core_service.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
@Data
@AllArgsConstructor
public class ApiResponse {
   private String message;
   private Object data;
}

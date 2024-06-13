package com.ahuynh.core_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Object> handleBaseException(BaseException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder().code(ex.getCode()).message(ex.getMessage()).build());
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleException(Exception e, Locale locale) {
        return ResponseEntity.badRequest().body("Exception: " + e.getMessage());
    }
}

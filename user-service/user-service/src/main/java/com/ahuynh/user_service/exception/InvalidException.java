package com.ahuynh.user_service.exception;

public class InvalidException extends BaseException {
    public InvalidException(String message, String code) {
        super(message, code);
    }
}

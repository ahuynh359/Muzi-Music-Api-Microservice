package com.ahuynh.notification_service.exception;

public class InvalidTokenException extends BaseException{
    public InvalidTokenException(String message) {
        super(message,ErrorCode.ERROR_INVALID_TOKEN);
    }
}

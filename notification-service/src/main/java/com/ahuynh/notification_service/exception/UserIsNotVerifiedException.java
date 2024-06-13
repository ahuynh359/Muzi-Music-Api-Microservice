package com.ahuynh.notification_service.exception;

public class UserIsNotVerifiedException extends BaseException{
    public UserIsNotVerifiedException(String message) {
        super(message,ErrorCode.ERROR_USER_NOT_VERIFIED);
    }
}


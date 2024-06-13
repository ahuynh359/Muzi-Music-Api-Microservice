package com.ahuynh.notification_service.exception;

public class PasswordIsIncorrectException extends BaseException{
    public PasswordIsIncorrectException(String mess) {
        super(mess, ErrorCode.ERROR_PASSWORD);
    }
}

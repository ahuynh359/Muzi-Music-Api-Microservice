package com.ahuynh.notification_service.exception;

public class UserAlreadyRegisteredException extends BaseException{
    public UserAlreadyRegisteredException(String message , String code) {
        super(message, code);
    }
}

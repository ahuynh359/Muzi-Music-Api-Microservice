package com.ahuynh.notification_service.exception;

public class EntityNotFoundException extends BaseException {


    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ERROR_ENTITY_NOT_FOUND);
    }
}

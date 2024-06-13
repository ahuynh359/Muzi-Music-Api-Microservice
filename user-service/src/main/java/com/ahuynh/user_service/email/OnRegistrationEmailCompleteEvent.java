package com.ahuynh.user_service.email;

import com.ahuynh.user_service.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnRegistrationEmailCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private UserEntity user;


    public OnRegistrationEmailCompleteEvent(UserEntity user, String appUrl) {
        super(user);
        this.appUrl = appUrl;
        this.user = user;

    }
}
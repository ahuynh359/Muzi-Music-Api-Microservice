package com.ahuynh.user_service.email;

import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@RequiredArgsConstructor
@Component
public class RegistrationEmailListener implements
        ApplicationListener<OnRegistrationEmailCompleteEvent> {

    private final VerificationTokenService verificationTokenService;
    private final JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationEmailCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationEmailCompleteEvent event) {
        UserEntity user = event.getUser();
        String token = getRandomNumberString();
        verificationTokenService.saveVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/auth/verifyEmail/" + token;
        String message = "Hi ," + user.getUsername() + " please verify opt";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + confirmationUrl);
        mailSender.send(email);
    }

    private String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}
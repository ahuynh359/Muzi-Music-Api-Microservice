package com.ahuynh.user_service.controller;

import com.ahuynh.user_service.email.OnRegistrationEmailCompleteEvent;
import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.model.entity.VerificationTokenEntity;
import com.ahuynh.user_service.model.rest.request.LoginRequest;
import com.ahuynh.user_service.model.rest.request.SignUpRequest;
import com.ahuynh.user_service.model.rest.response.ApiResponse;
import com.ahuynh.user_service.model.rest.response.MessageResponse;
import com.ahuynh.user_service.model.rest.response.UserResponse;
import com.ahuynh.user_service.service.AuthService;
import com.ahuynh.user_service.service.VerificationTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final VerificationTokenService verificationTokenService;

    private final ObjectMapper objectMapper;

    public String getCurrentUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @GetMapping()
    public String test() {
        return "Hello World";
    }

    /**
     * Đăng kí
     * Ai cũng được
     * Trả về message
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request, final HttpServletRequest httpServletRequest) {
        UserEntity user = authService.createUser(request);
        applicationEventPublisher.publishEvent(new OnRegistrationEmailCompleteEvent(user, getCurrentUrl(httpServletRequest)));

        return
                new ResponseEntity<>(new MessageResponse
                        ("Sign in success"), HttpStatus.OK);
    }

    /**
     * Verify email
     * Ai cũng được
     * Trả về message
     */
    @GetMapping("/verify/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable("token") String token) {
        VerificationTokenEntity verificationToken = verificationTokenService.getJwt(token);
        return
                new ResponseEntity<>(new MessageResponse
                        ("Verify email successfully"), HttpStatus.OK);
    }

    /**
     * Đăng nhập
     * Ai cũng được
     * Trả về user dang nhap
     */
    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest request) {
        UserResponse user = authService.login(request);
        return
                new ResponseEntity<>(new ApiResponse
                        ("Verify email successfully", user), HttpStatus.OK);
    }


    /**
     * Resend otp
     * Ai cũng được
     * Tra ve message
     */
    @PostMapping("/resend/{email}")
    public ResponseEntity<?> resendOtp(@PathVariable String email,
                                       final HttpServletRequest request) {
        UserEntity user = authService.resendOtp(email);
        applicationEventPublisher.publishEvent(new OnRegistrationEmailCompleteEvent
                (user, getCurrentUrl(request)));
        return new ResponseEntity<>(
                new MessageResponse(
                        "Resent otp successfully"), HttpStatus.OK);
    }


}

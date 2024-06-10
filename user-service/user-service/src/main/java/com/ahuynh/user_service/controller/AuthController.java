package com.ahuynh.user_service.controller;

import com.ahuynh.user_service.email.OnRegistrationEmailCompleteEvent;
import com.ahuynh.user_service.model.dto.LoginResponse;
import com.ahuynh.user_service.model.dto.UserDto;
import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.model.entity.VerificationTokenEntity;
import com.ahuynh.user_service.model.mapper.UserMapper;
import com.ahuynh.user_service.model.rest.request.LoginRequest;
import com.ahuynh.user_service.model.rest.response.ApiResponse;
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
    private final UserMapper userMapper = new UserMapper();

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
     * Trả về UserDto - ko lay
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDto request, final HttpServletRequest httpServletRequest) {
        UserDto user = authService.createUser(request);
        applicationEventPublisher.publishEvent(new OnRegistrationEmailCompleteEvent(userMapper.convertToEntity(user), getCurrentUrl(httpServletRequest)));

        return
                new ResponseEntity<>(new ApiResponse
                        ("Sign in success"), HttpStatus.OK);
    }

    /**
     * Đăng kí xong thì verify email
     * Ai cũng được
     * Trả về VerificationToken - ko lay
     */
    @GetMapping("/verifyEmail/{token}")
    public ResponseEntity<?> verifyEmail(@PathVariable("token") String token) {
        VerificationTokenEntity verificationToken = verificationTokenService.getJwt(token);
        return
                new ResponseEntity<>(new ApiResponse
                        ("Verify email successfully"), HttpStatus.OK);
    }

    /**
     * Đăng nhập
     * Ai cũng được
     * Trả về token dang nhap
     */
    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest request) {
        UserDto user = authService.login(request);
        return new ResponseEntity<>(
                new LoginResponse(
                        "Sign in successfully","",user), HttpStatus.CREATED);
    }


    /**
     * Resend otp
     * Ai cũng được
     * Tra ve user - ko lay
     */
    @PostMapping("/resend-otp/{email}")
    public ResponseEntity<?> resendOtp(@PathVariable String email,
                                       final HttpServletRequest request) {
        UserEntity user = authService.resendOtp(email);
        applicationEventPublisher.publishEvent(new OnRegistrationEmailCompleteEvent
                (user, getCurrentUrl(request)));
        return new ResponseEntity<>(
                new ApiResponse(
                        "Resent otp successfully"), HttpStatus.CREATED);
    }


}

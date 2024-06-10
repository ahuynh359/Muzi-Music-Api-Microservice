package com.ahuynh.user_service.service;

import com.ahuynh.user_service.exception.InvalidTokenException;
import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.model.entity.VerificationTokenEntity;
import com.ahuynh.user_service.model.repository.UserRepository;
import com.ahuynh.user_service.model.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationTokenService {


    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;


    public void saveVerificationToken(UserEntity user, String token) {

        VerificationTokenEntity verificationToken = new VerificationTokenEntity(user, token);
        verificationTokenRepository.save(verificationToken);

        log.info("Verification token saved: " + verificationToken);

    }

    public VerificationTokenEntity getJwt(String token) {
        VerificationTokenEntity verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            throw new InvalidTokenException("Token is invalid");
        }
        if (verificationToken.getUser().isEnabled()) {
            throw new InvalidTokenException("This account has been verified");
        }

        String verificationResult = validateToken(verificationToken);

        if (verificationResult.equalsIgnoreCase("Valid")) {
            return verificationToken;
        }
        throw new InvalidTokenException("Invalid verification token");
    }

    private String validateToken(VerificationTokenEntity verificationToken) {
        UserEntity user = verificationToken.getUser();
        if (verificationToken.getExpiryTime().compareTo(Instant.now()) < 0) {
            verificationTokenRepository.delete(verificationToken);
            return "Token is expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "Valid";
    }


}
package com.ahuynh.user_service.service;

import com.ahuynh.user_service.exception.*;
import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.model.entity.VerificationTokenEntity;
import com.ahuynh.user_service.model.entity.role.RoleEntity;
import com.ahuynh.user_service.model.entity.role.RoleName;
import com.ahuynh.user_service.model.repository.RoleRepository;
import com.ahuynh.user_service.model.repository.UserRepository;
import com.ahuynh.user_service.model.repository.VerificationTokenRepository;
import com.ahuynh.user_service.model.rest.request.LoginRequest;
import com.ahuynh.user_service.model.rest.request.SignUpRequest;
import com.ahuynh.user_service.model.rest.response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public UserEntity createUser(SignUpRequest user) {
        //Check exception
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyRegisteredException("This email already registered. Please try other email.",
                    ErrorCode.ERROR_EMAIL_REGISTERED);
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyRegisteredException("This username already registered. Please try other username.",
                    ErrorCode.ERROR_USER_NAME_REGISTERED);
        }


        //Save user

        Set<RoleEntity> roles = new HashSet<>();
        if (userRepository.count() == 0) {
            roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new EntityNotFoundException("There is no role in db")));
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new EntityNotFoundException("There is no role in db")));

        } else {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new EntityNotFoundException("There is no role in db")));
        }

        return userRepository.save(new UserEntity(user.getEmail(), user.getPassword(), user.getUsername(), roles, "", false));

    }


    public UserResponse login(LoginRequest loginRequest) {

        UserEntity user = userRepository.
                getByUsernameOrEmail(loginRequest.getUserNameOrEmail(),
                        loginRequest.getUserNameOrEmail());
        if (!user.isEnabled()) {
            throw new UserIsNotVerifiedException("User " + user.getUsername() + " is not verify. Please resent otp to emai;");
        }

        if (user.getPassword().equals(loginRequest.getPassword()) && user.getEmail().equals(loginRequest.getUserNameOrEmail())) {
            return UserResponse.toUserResponse(user);
        }

        throw new InvalidException("User or password is incorrect", ErrorCode.ERROR_INVALID_USER);
    }

    public UserEntity resendOtp(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("This user is not in db " + email));

        VerificationTokenEntity verificationToken = verificationTokenRepository.findByUser(user);
        if (verificationToken != null) {
            verificationTokenRepository.delete(verificationToken);
        }
        if (user.isEnabled()) {
            throw new UserAlreadyRegisteredException("User is enable", ErrorCode.ERROR_INVALID_TOKEN);
        }
        return user;

    }
}
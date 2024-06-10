package com.ahuynh.user_service.service;

import com.ahuynh.user_service.exception.*;
import com.ahuynh.user_service.model.dto.RoleDto;
import com.ahuynh.user_service.model.dto.UserDto;
import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.model.entity.VerificationTokenEntity;
import com.ahuynh.user_service.model.entity.role.RoleEntity;
import com.ahuynh.user_service.model.entity.role.RoleName;
import com.ahuynh.user_service.model.mapper.RoleMapper;
import com.ahuynh.user_service.model.mapper.UserMapper;
import com.ahuynh.user_service.model.repository.RoleRepository;
import com.ahuynh.user_service.model.repository.UserRepository;
import com.ahuynh.user_service.model.repository.VerificationTokenRepository;
import com.ahuynh.user_service.model.rest.request.LoginRequest;
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
    private final UserMapper userMapper = new UserMapper();
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public UserDto createUser(UserDto user) {
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

        UserEntity userEntity = userMapper.convertToEntity(user);
        userEntity.setPassword(user.getPassword());
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
        userEntity.setRole(roles);
        userEntity = userRepository.save(userEntity);
        log.info("Created user: {}", user);
        return userMapper.convertToDto(userEntity);

    }


    public UserDto login(LoginRequest loginRequest) {

        UserDto user = userMapper.convertToDto(userRepository.getByUsernameOrEmail(loginRequest.getUserNameOrEmail(), loginRequest.getUserNameOrEmail()));
        if (!user.isEnabled()) {
            throw new UserIsNotVerifiedException("User " + user.getUsername() + " is not verify. Please resent otp to emai;");
        }
        log.info(user.getUsername());
        log.info(user.getPassword());
        log.info(loginRequest.getPassword());
        log.info(loginRequest.getUserNameOrEmail());

        if (user.getPassword().equals(loginRequest.getPassword()) && user.getEmail().equals(loginRequest.getUserNameOrEmail())) {
            return user;
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
package com.ahuynh.user_service.service;

import com.ahuynh.user_service.exception.EntityNotFoundException;
import com.ahuynh.user_service.exception.ErrorCode;
import com.ahuynh.user_service.exception.InvalidException;
import com.ahuynh.user_service.exception.PasswordIsIncorrectException;
import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.model.entity.role.RoleEntity;
import com.ahuynh.user_service.model.entity.role.RoleName;
import com.ahuynh.user_service.model.repository.RoleRepository;
import com.ahuynh.user_service.model.repository.UserRepository;
import com.ahuynh.user_service.model.rest.request.ChangeDeviceTokenRequest;
import com.ahuynh.user_service.model.rest.request.ChangePasswordRequest;
import com.ahuynh.user_service.model.rest.response.SongResponse;
import com.ahuynh.user_service.model.rest.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FirebaseService firebaseService;
    private final RoleRepository roleRepository;
    private final FeignClientSong feignClientSong;


    public UserResponse createUser(String email, String password, String username, MultipartFile avatar, boolean enable) {
        if (userRepository.existsByEmail(email)) {
            throw new InvalidException("Email already exists", ErrorCode.ERROR_EMAIL_REGISTERED);
        }

        if (userRepository.existsByUsername(username)) {
            throw new InvalidException("User already exists", ErrorCode.ERROR);
        }

        //Lưu UserEntity vào db
        Set<RoleEntity> roles = new HashSet<>();
        if (userRepository.count() == 0) {
            roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new InvalidException("There is no role in db", ErrorCode.ERROR)));
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new InvalidException("There is no role in db", ErrorCode.ERROR)));

        } else {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new InvalidException("There is no role in db", ErrorCode.ERROR)));
        }
        String url = firebaseService.upload(avatar, "image/png");
        UserEntity user = new UserEntity(email, password, username, roles, url, enable);

        return UserResponse.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserEntity not exits id =" + id));
        userRepository.deleteById(id);
    }

    public List<UserResponse> getAllUser() {
        return UserResponse.toResponseList(userRepository.findAll());
    }

    public UserResponse getUserById(Long id) {
        return UserResponse.toUserResponse(userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not exits id =" + id)));
    }

    public UserResponse changeDevice(ChangeDeviceTokenRequest request) {
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(() ->
                new EntityNotFoundException("User not exits id =" + request.getUserId()));
        user.setDeviceToken(request.getDeviceToken());
        return UserResponse.toUserResponse(userRepository.save(user));
    }

    public UserResponse changePassword(ChangePasswordRequest request) {
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(() ->
                new EntityNotFoundException("User not exits id =" + request.getUserId()));
        if (!request.getOldPassword().equals(user.getPassword())) {
            throw new PasswordIsIncorrectException("Old password is incorrect");
        }


        user.setPassword(request.getNewPassword());

        return UserResponse.toUserResponse(userRepository.save(user));
    }

    public UserResponse changeAvatar(Long userId, MultipartFile avatar) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not exits id =" + userId));
        String url = firebaseService.upload(avatar, "image/png");
        user.setAvatar(url);
        return UserResponse.toUserResponse(userRepository.save(user));

    }

    public UserResponse lockUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not exits id =" + id));
        user.setEnabled(false);
        return UserResponse.toUserResponse(userRepository.save(user));
    }


    public UserResponse unlockUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not exits id =" + id));
        user.setEnabled(true);
        return UserResponse.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getHotUser() {
        return UserResponse.toResponseList(userRepository.findHotUser());
    }

    public List<UserEntity> findByUsernameContainingIgnoreCase(String keyword) {
        return userRepository.findByUsernameContainingIgnoreCase(keyword);
    }

    public SongResponse getSong(Long id) {
        return feignClientSong.getSongById(id);
    }

    public Set<SongResponse> getLoveSongOfUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found " + id.toString()));
        Set<Long> songIds = user.getLovedSongId();
        return feignClientSong.getListSongById(songIds);
    }

    public UserEntity loveSong(Long userId, Long songId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("Not found " + userId.toString()));
        user.addLoveSong(songId);
        return userRepository.save(user);
    }

    public UserEntity unloveSong(Long userId, Long songId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("Not found " + userId.toString()));
        user.removeLoveSong(songId);
        return userRepository.save(user);
    }
}

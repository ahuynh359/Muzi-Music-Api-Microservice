package com.ahuynh.user_service.controller;

import com.ahuynh.user_service.model.rest.request.AvatarRequest;
import com.ahuynh.user_service.model.rest.request.ChangeDeviceTokenRequest;
import com.ahuynh.user_service.model.rest.request.ChangePasswordRequest;
import com.ahuynh.user_service.model.rest.response.ApiResponse;
import com.ahuynh.user_service.model.rest.response.MessageResponse;
import com.ahuynh.user_service.model.rest.response.SongResponse;
import com.ahuynh.user_service.model.rest.response.UserResponse;
import com.ahuynh.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * Thêm user - co ca avatar
     * ADMIN
     * tra ve user dto
     */
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestParam("email") String email,
                                        @RequestParam("password") String password,
                                        @RequestParam("username") String username,
                                        @RequestParam("avatar") MultipartFile avatar,
                                        @RequestParam("enable") boolean enable) {
        return new ResponseEntity<>(new ApiResponse("Success", userService.createUser(email, password, username, avatar, enable)),
                HttpStatus.OK);
    }

    /**
     * Xóa người dùng
     * ADMIN
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new MessageResponse("Success"),
                HttpStatus.OK);
    }


    /**
     * Mở khóa người dùng
     * ADMIN
     */
    @PutMapping("/unlock/{id}")
    public ResponseEntity<?> unlock(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new ApiResponse("Success", userService.unlockUser(id)),
                HttpStatus.OK);
    }

    /**
     * Khóa người dùng
     * ADMIN
     */
    @PutMapping("/lock/{id}")
    public ResponseEntity<?> lockUser(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new ApiResponse("Success", userService.lockUser(id)),
                HttpStatus.OK);
    }

    /**
     * Lấy thông tin tất cả người dùng
     * ADMIN - USER
     * List<UserDto>
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(new ApiResponse("Success", userService.getAllUser()),
                HttpStatus.OK);
    }

    /**
     * Lấy ds tài khoản nổi bật
     * ADMIN - USER
     * List<UserDto>
     */
    @GetMapping("/hot")
    public ResponseEntity<?> getHotUser() {
        return new ResponseEntity<>(new ApiResponse("Success", userService.getHotUser()),
                HttpStatus.OK);


    }


    /**
     * Lấy thông tin 1 người dùng
     * ADMIN - USER
     * UserDto
     */
    @GetMapping("/information/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ApiResponse("Success", userService.getUserById(id)),
                HttpStatus.OK);
    }

    /**
     * Thay đổi thông tin device
     * ADMIN - USER
     */
    @PutMapping("/change/device")
    public ResponseEntity<?> changeDevice(@Valid @RequestBody ChangeDeviceTokenRequest request) {
        UserResponse response = userService.changeDevice(request);
        return new ResponseEntity<>(new ApiResponse("Success", response),
                HttpStatus.OK);
    }

    /**
     * Thay đổi thông tin password
     * ADMIN - USER
     */
    @PutMapping("/change/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        return new ResponseEntity<>(new ApiResponse("Success", userService.changePassword(request)),
                HttpStatus.OK);
    }

    /**
     * Thay đổi avatar
     * USER - ADMIN
     */
    @PostMapping("/change/avatar/{id}")
    public ResponseEntity<?> changeAvatar(@PathVariable("id") Long userId,@RequestBody AvatarRequest url) {
        return new ResponseEntity<>(new ApiResponse("Success", userService.changeAvatar(userId, url)),
                HttpStatus.OK);
    }

    /**
     * Lấy ds tài khoản nổi bật
     * ADMIN - USER
     * List<UserDto>
     */
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchUser(@PathVariable String keyword) {
        return ResponseEntity.ok(UserResponse.toResponseList(userService.findByUsernameContainingIgnoreCase(keyword)));

    }


    @GetMapping("get/love/{id}")
    public ResponseEntity<?> getAllLoveSongOfUser(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse("Success", userService.getLoveSongOfUser(id)),
                HttpStatus.OK);


    }

    @GetMapping("/{userId}/is/love/{songId}")
    public ResponseEntity<?> isLoveSong(@PathVariable Long userId, @PathVariable Long songId) {
        ;
        return new ResponseEntity<>(new ApiResponse("Success",userService.isLoveSong(userId, songId)),
                HttpStatus.OK);


    }

    @PostMapping("/{userId}/love/{songId}")
    public ResponseEntity<?> loveSong(@PathVariable Long userId, @PathVariable Long songId) {
        userService.loveSong(userId, songId);
        return new ResponseEntity<>(new MessageResponse("Success" ),
                HttpStatus.OK);
    }


}


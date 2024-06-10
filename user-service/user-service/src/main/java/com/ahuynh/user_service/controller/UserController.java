package com.ahuynh.user_service.controller;

import com.ahuynh.user_service.model.dto.UserDto;
import com.ahuynh.user_service.model.rest.request.ChangeDeviceTokenRequest;
import com.ahuynh.user_service.model.rest.request.ChangePasswordRequest;
import com.ahuynh.user_service.model.rest.response.ApiResponse;
import com.ahuynh.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping()
    public ResponseEntity<?> addUser(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("username") String username,
                                     @RequestParam("avatar") MultipartFile avatar,
                                     @RequestParam("enable") boolean enable) {

        return ResponseEntity.ok(userService.save(email, password, username, avatar, enable));
    }

    /**
     * Xóa người dùng
     * ADMIN
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete User Ok");
    }


    /**
     * Mở khóa người dùng
     * ADMIN
     */
    @PutMapping("/unlock/{id}")
    public ResponseEntity<?> unlock(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.unlockUser(id));
    }

    /**
     * Khóa người dùng
     * ADMIN
     */
    @PutMapping("/lock/{id}")
    public ResponseEntity<?> lockUser(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.lockUser(id));
    }

    /**
     * Lấy thông tin tất cả người dùng
     * ADMIN - USER
     * List<UserDto>
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    /**
     * Lấy ds tài khoản nổi bật
     * ADMIN - USER
     * List<UserDto>
     */
    @GetMapping("/hot")
    public ResponseEntity<?> getHotUser() {
        return ResponseEntity.ok(userService.getHotUser());
    }


    /**
     * Lấy thông tin 1 người dùng
     * ADMIN - USER
     * UserDto
     */
    @GetMapping("/information/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Thay đổi thông tin device
     * ADMIN - USER
     */
    @PutMapping("/change/device")
    public ResponseEntity<?> changeToken(@Valid @RequestBody ChangeDeviceTokenRequest request) {
        UserDto userDto = userService.changeDevice(request);
        return new ResponseEntity<>(new ApiResponse("Success"),
                HttpStatus.OK);
    }

    /**
     * Thay đổi thông tin password
     * ADMIN - USER
     */
    @PutMapping("/change/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(userService.changePassword(request));
    }

    /**
     * Thay đổi avatar
     * USER - ADMIN
     */
    @PutMapping("/change/avatar/{id}")
    public ResponseEntity<?> changeAvatar(@PathVariable("id") Long userId, MultipartFile avatar) {
        return ResponseEntity.ok(userService.changeAvatar(userId, avatar));

    }


}


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

//    /**
//     * Thêm người dùng chua lam xac thuc otp cho nay
//     * ADMIN
//     */
//    @PostMapping()
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> addUser(@RequestParam("email") String email,
//                                     @RequestParam("password") String password,
//                                     @RequestParam("username") String username,
//                                     @RequestParam("avatar") MultipartFile avatar,
//                                     @RequestParam("enable") boolean enable) {
//
//        User user = userService.save(email, password, username, avatar, enable);
//        return new ResponseEntity<>(new ApiResponse(true, "Create Successfully",
//                user), HttpStatus.CREATED);
//    }
//
//    /**
//     * Xóa người dùng
//     * ADMIN
//     */
//    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Delete Successfully",
//                ""), HttpStatus.OK);
//    }
//
//    /**
//     * Sửa người dùng - ko có avatar
//     * ADMIN, USER
//     */
//    @PutMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id,
//                                        @RequestBody UserRequest userRequest) {
//        User user = userService.updateUser(id, userRequest);
//        return new ResponseEntity<>(new ApiResponse(true, "Update Successfully",
//                user), HttpStatus.OK);
//    }
//
//
//    /**
//     * Mở khóa người dùng
//     * ADMIN
//     */
//    @PutMapping("/enable/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') ")
//    public ResponseEntity<?> unlock(@PathVariable(name = "id") Long id) {
//        User user = userService.updateEnable(id, true);
//        return new ResponseEntity<>(new ApiResponse(true, "Update Successfully", objectMapper.convertValue(user, User.class)), HttpStatus.OK);
//    }
//
//    /**
//     * Khóa người dùng
//     * ADMIN
//     */
//    @PutMapping("/disable/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') ")
//    public ResponseEntity<?> lock(@PathVariable(name = "id") Long id) {
//        User user = userService.updateEnable(id, false);
//        return new ResponseEntity<>(new ApiResponse(true, "Update Successfully",
//                user), HttpStatus.OK);
//    }

    /**
     * Lấy thông tin tất cả người dùng
     * ADMIN - USER
     * List<UserDto>
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok( userService.getAllUser());
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
        return new ResponseEntity<>(new ApiResponse("Success", userDto),
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
     *
     */
    @PutMapping("/change/avatar/{id}")
    public ResponseEntity<?> changeAvatar(@PathVariable("id") Long userId, MultipartFile avatar) {
        return ResponseEntity.ok(userService.changeAvatar(userId,avatar));

    }



}


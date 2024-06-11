package com.ahuynh.user_service.model.rest.response;


import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.model.entity.role.RoleEntity;
import com.ahuynh.user_service.model.entity.role.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private String avatar;
    private String deviceToken;
    private boolean enabled;
    private List<String> role;

    public static UserResponse toUserResponse(UserEntity user) {
        UserResponse userResponse = new UserResponse();
        userResponse.id = user.getId();
        userResponse.email = user.getEmail();
        userResponse.username = user.getUsername();
        userResponse.avatar = user.getAvatar();
        userResponse.enabled = user.isEnabled();
        userResponse.deviceToken = user.getDeviceToken();
        userResponse.role = user.getRole() // Assuming this returns a List<RoleEntity>
                .stream()
                .map(RoleEntity::getName)
                .map(RoleName::toString) // This converts the enum to a String
                .collect(Collectors.toList());

        return userResponse;

    }

    public static List<UserResponse> toResponseList(List<UserEntity> users) {
        return users.stream()
                .map(UserResponse::toUserResponse)
                .collect(Collectors.toList());


    }
}
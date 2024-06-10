package com.ahuynh.user_service.model.dto;

import com.ahuynh.user_service.model.entity.role.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private RoleName name;
}

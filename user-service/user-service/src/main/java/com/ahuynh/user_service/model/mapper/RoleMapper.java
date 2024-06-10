package com.ahuynh.user_service.model.mapper;

import com.ahuynh.user_service.model.dto.RoleDto;
import com.ahuynh.user_service.model.entity.role.RoleEntity;
import org.springframework.beans.BeanUtils;

public class RoleMapper extends BaseMapper<RoleEntity, RoleDto> {

    @Override
    public RoleEntity convertToEntity(RoleDto dto, Object... args) {
        RoleEntity roleEntity = new RoleEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, roleEntity);
        }
        return roleEntity;
    }

    @Override
    public RoleDto convertToDto(RoleEntity entity, Object... args) {
        RoleDto roleDto = new RoleDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, roleDto);
        }
        return roleDto;
    }


}
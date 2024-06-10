package com.ahuynh.user_service.model.mapper;

import com.ahuynh.user_service.model.dto.UserDto;
import com.ahuynh.user_service.model.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class UserMapper extends BaseMapper<UserEntity, UserDto> {

    @Override
    public UserEntity convertToEntity(UserDto dto, Object... args) {
        UserEntity userEntity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, userEntity);
        }
        return userEntity;
    }

    @Override
    public UserDto convertToDto(UserEntity entity, Object... args) {
        UserDto userDto = new UserDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, userDto);
        }
        return userDto;
    }


}

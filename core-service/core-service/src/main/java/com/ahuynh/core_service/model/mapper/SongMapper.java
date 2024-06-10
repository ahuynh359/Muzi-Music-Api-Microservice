package com.ahuynh.core_service.model.mapper;

import com.ahuynh.core_service.model.dto.SongDto;
import com.ahuynh.core_service.model.dto.UserDto;
import com.ahuynh.core_service.model.entity.SongEntity;
import org.springframework.beans.BeanUtils;

public class SongMapper extends BaseMapper<SongEntity, SongDto> {


    @Override
    public SongEntity convertToEntity(UserDto dto, Object... args) {
        SongEntity songEntity = new SongEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, songEntity);
        }
        return songEntity;
    }


    @Override
    public SongEntity convertToEntity(SongDto dto, Object... args) {
        return null;
    }

    @Override
    public SongDto convertToDto(SongEntity entity, Object... args) {
        SongDto songDto = new SongDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, songDto);
        }
        return songDto;
    }


}

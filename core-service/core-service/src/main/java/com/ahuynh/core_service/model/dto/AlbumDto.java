package com.ahuynh.core_service.model.dto;

import com.ahuynh.core_service.model.entity.AlbumEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {
    private Long id;
    private String name;
    private String avatar;

    public static AlbumDto toDto(AlbumEntity album) {
        AlbumDto response = new AlbumDto();
        response.id = album.getId();
        response.avatar = album.getAvatar();
        response.name = album.getName();
        return response;
    }

    public static List<AlbumDto> toResponseList(List<AlbumEntity> albums) {
        return albums.stream()
                .map(AlbumDto::toDto)
                .collect(Collectors.toList());


    }
}

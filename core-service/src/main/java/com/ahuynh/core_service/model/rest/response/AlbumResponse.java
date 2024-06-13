package com.ahuynh.core_service.model.rest.response;

import com.ahuynh.core_service.model.entity.AlbumEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse {
    private Long id;
    private String name;
    private String avatar;

    public static AlbumResponse toResponse(AlbumEntity album) {
        AlbumResponse response = new AlbumResponse();
        response.id = album.getId();
        response.avatar = album.getAvatar();
        response.name = album.getName();
        return response;
    }

    public static List<AlbumResponse> toResponseList(List<AlbumEntity> albums) {
        return albums.stream()
                .map(AlbumResponse::toResponse)
                .collect(Collectors.toList());


    }
}
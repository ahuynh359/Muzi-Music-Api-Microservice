package com.ahuynh.core_service.model.rest.response;

import com.ahuynh.core_service.model.entity.PlaylistEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class PlaylistResponse {
    private Long id;
    private String name;
    private Long userId;
    private String avatar;



    public static List<PlaylistResponse> toResponseList(List<PlaylistEntity> playlists) {
        return playlists.stream()
                .map(PlaylistResponse::toResponse)
                .collect(Collectors.toList());


    }

    public static PlaylistResponse toResponse(PlaylistEntity playlist) {
        PlaylistResponse response = new PlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setAvatar(playlist.getAvatar());
        response.setUserId(playlist.getUserId());

        return response;
    }
}
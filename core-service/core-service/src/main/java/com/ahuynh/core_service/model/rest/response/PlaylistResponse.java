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
    private List<SongResponse> songs = new ArrayList<>();


    public static List<PlaylistResponse> toResponseList(List<PlaylistEntity> playlists) {
        return playlists.stream()
                .map(PlaylistResponse::toResponse)
                .collect(Collectors.toList());


    }

    public static PlaylistResponse toResponse(PlaylistEntity playlist) {
        PlaylistResponse response = new PlaylistResponse();
        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setUserId(playlist.getUserId());
        List<SongResponse> songResponses = new ArrayList<>();
        for (SongEntity song : playlist.getSongs()) {
            songResponses.add(SongResponse.toResponse(song));
        }
        response.setSongs(songResponses);
        return response;
    }
}
package com.ahuynh.core_service.model.rest.response;

import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.entity.TypeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongResponse {
    private Long id;
    private String name;
    private String avatar;
    private String file;
    private String lyrics;
    private String album;
    private Long listen;
    private String singer;
    private List<String> types = new ArrayList<>();

    public static SongResponse toResponse(SongEntity song) {
        SongResponse response = new SongResponse();
        response.id = song.getId();
        response.name = song.getName();
        response.avatar = song.getAvatar();
        response.file = song.getFile();
        response.lyrics = song.getLyrics();
        response.album = song.getAlbum().getName();
        response.listen = song.getListen();
        response.singer = song.getSinger();
        response.types = song.getTypes() // Assuming this returns a List<RoleEntity>
                .stream()
                .map(TypeEntity::getName)
                .collect(Collectors.toList());
        return response;


    }

    public static List<SongResponse> toResponseList(List<SongEntity> songs) {
        return songs.stream()
                .map(SongResponse::toResponse)
                .collect(Collectors.toList());


    }

    public static Set<SongResponse> toResponseSet(Set<SongEntity> songs) {
        return songs.stream()
                .map(SongResponse::toResponse)
                .collect(Collectors.toSet());
    }
}

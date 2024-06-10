package com.ahuynh.core_service.model.dto;

import com.ahuynh.core_service.model.entity.SongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SongDto {
    private Long id;
    private String name;
    private String avatar;
    private String file;
    private String lyrics;
    private AlbumDto album;
    private Long listen;
    private String singer;
    private List<TypeDto> types = new ArrayList<>();

    public static SongDto toDto(SongEntity song) {
        SongDto dto = new SongDto();
        dto.id = song.getId();
        dto.name = song.getName();
        dto.avatar = song.getAvatar();
        dto.file = song.getFile();
        dto.lyrics = song.getLyrics();
        dto.album = AlbumDto.toDto(song.getAlbum());
        dto.listen = song.getListen();
        dto.singer = song.getSinger();

        List<TypeDto> typeResponse = new ArrayList<>();
        song.getTypes().forEach(type -> {
            typeResponse.add(TypeDto.toDto(type));
        });
        dto.types = typeResponse;
        return dto;


    }

    public static List<SongDto> toDtoList(List<SongEntity> songs) {
        return songs.stream()
                .map(SongDto::toDto)
                .collect(Collectors.toList());


    }
}

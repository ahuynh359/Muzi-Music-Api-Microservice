package com.ahuynh.user_service.model.rest.response;


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
    private Long listen;
    private String singer;
    private AlbumResponse album;
    private List<String> types = new ArrayList<>();



}

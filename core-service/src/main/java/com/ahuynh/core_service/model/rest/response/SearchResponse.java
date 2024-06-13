package com.ahuynh.core_service.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    private List<SongResponse> songs;
    private List<AlbumResponse> albums;
    private List<UserResponse> users;
}

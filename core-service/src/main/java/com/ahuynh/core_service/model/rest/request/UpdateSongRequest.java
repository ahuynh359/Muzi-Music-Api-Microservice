package com.ahuynh.core_service.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSongRequest {
    private String name;
    private Long albumId;
    private String lyrics;
    private String singer;

}

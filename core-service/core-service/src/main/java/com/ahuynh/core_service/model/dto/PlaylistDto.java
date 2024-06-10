package com.ahuynh.core_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaylistDto {
    private String name;
    private Long userId;
}

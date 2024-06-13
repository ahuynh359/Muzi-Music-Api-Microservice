package com.ahuynh.core_service.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAlbumRequest {
    private Long id;
    private String name;
    private String avatar;



}
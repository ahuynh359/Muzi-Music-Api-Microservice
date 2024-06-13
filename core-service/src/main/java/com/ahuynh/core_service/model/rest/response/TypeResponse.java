package com.ahuynh.core_service.model.rest.response;

import com.ahuynh.core_service.model.entity.TypeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TypeResponse {
    private Long id;
    private String name;

    public static TypeResponse toResponse(TypeEntity type) {
        return new TypeResponse(type.getId(), type.getName());

    }

    public static List<TypeResponse> toResponseList(List<TypeEntity> types) {
        return types.stream()
                .map(TypeResponse::toResponse)
                .collect(Collectors.toList());


    }
}
package com.ahuynh.core_service.model.dto;

import com.ahuynh.core_service.model.entity.TypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TypeDto {
    private Long id;
    private String name;

    public static TypeDto toDto(TypeEntity type) {
        return new TypeDto(type.getId(), type.getName());

    }

    public static List<TypeDto> toDtoList(List<TypeEntity> types) {
        return types.stream()
                .map(TypeDto::toDto)
                .collect(Collectors.toList());


    }

}

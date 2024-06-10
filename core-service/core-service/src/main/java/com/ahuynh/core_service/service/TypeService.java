package com.ahuynh.core_service.service;

import com.ahuynh.core_service.controller.TypeController;
import com.ahuynh.core_service.model.entity.TypeEntity;
import com.ahuynh.core_service.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public List<TypeEntity> getAllType() {
        return typeRepository.findAll();
    }
}

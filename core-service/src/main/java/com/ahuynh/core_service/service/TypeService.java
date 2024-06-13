package com.ahuynh.core_service.service;

import com.ahuynh.core_service.controller.TypeController;
import com.ahuynh.core_service.exception.EntityNotFoundException;
import com.ahuynh.core_service.exception.ErrorCode;
import com.ahuynh.core_service.exception.InvalidException;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.entity.TypeEntity;
import com.ahuynh.core_service.model.rest.request.TypeRequest;
import com.ahuynh.core_service.repository.TypeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeEntity createType(TypeRequest typeRequest) {
        if(typeRepository.existsByName(typeRequest.getName())){
            throw new InvalidException("Type with name " + typeRequest.getName() + " already exists", ErrorCode.ERROR);
        }
        TypeEntity type = new TypeEntity(typeRequest.getName());
        return typeRepository.save(type);
    }



    public void deleteType(Long id) {
        typeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Type with id " + id + " not found"));
        typeRepository.deleteById(id);
    }

    public TypeEntity updateType(Long id, TypeRequest typeRequest) {
        TypeEntity updateType = typeRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Type not exits id =" + id));


        if (typeRequest.getName() != null) {
            updateType.setName(typeRequest.getName());
        }

        return typeRepository.save(updateType);
    }

    public List<TypeEntity> getAllType() {
        return typeRepository.findAll();
    }

    public List<SongEntity> getSongFromType(Long id) {
        if(!typeRepository.existsById(id)){
            throw new EntityNotFoundException("Type with id " + id + " not found");
        }
        return typeRepository.findSongById(id);

    }
}
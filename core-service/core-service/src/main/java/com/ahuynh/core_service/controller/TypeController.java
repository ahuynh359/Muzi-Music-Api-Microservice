package com.ahuynh.core_service.controller;

import com.ahuynh.core_service.model.dto.TypeDto;
import com.ahuynh.core_service.model.entity.TypeEntity;
import com.ahuynh.core_service.model.rest.response.ApiResponse;
import com.ahuynh.core_service.service.TypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;
    private final ObjectMapper objectMapper;
//
//    @PostMapping()
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> addType(@Valid @RequestBody TypeRequest request) {
//
//        Type type = typeService.save(request);
//        return new ResponseEntity<>(new ApiResponse(true, "Create Successfully",
//                objectMapper.convertValue(type, TypeResponse.class)), HttpStatus.CREATED);
//    }
//
//    @GetMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getTypeById(@PathVariable(name = "id") Long id) {
//        Type type = typeService.getType(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully", objectMapper.convertValue(type, TypeResponse.class)), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> deleteType(@PathVariable(name = "id") Long id) {
//        typeService.deleteType(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Delete Successfully", ""), HttpStatus.OK);
//    }
//
//    @PutMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> updateType(@PathVariable(name = "id") Long id, @RequestBody TypeRequest request) {
//        Type type = typeService.updateType(id, request);
//        return new ResponseEntity<>(new ApiResponse(true, "Update  Successfully",
//                objectMapper.convertValue(type, TypeResponse.class)), HttpStatus.OK);
//    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllType() {
        List<TypeEntity> type = typeService.getAllType();
        List<TypeDto> response = TypeDto.toDtoList(type);
        return new ResponseEntity<>(new ApiResponse("Successfully", response), HttpStatus.OK);
    }

//
//    @GetMapping("/songs/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getSongFromType(@PathVariable(name = "id") Long id) {
//        List<Song> songs = typeService.getSongFromType(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully", songs), HttpStatus.OK);
//    }




}
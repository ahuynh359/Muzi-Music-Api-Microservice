package com.ahuynh.core_service.controller;

import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.entity.TypeEntity;
import com.ahuynh.core_service.model.rest.request.TypeRequest;
import com.ahuynh.core_service.model.rest.response.ApiResponse;
import com.ahuynh.core_service.model.rest.response.MessageResponse;
import com.ahuynh.core_service.model.rest.response.SongResponse;
import com.ahuynh.core_service.model.rest.response.TypeResponse;
import com.ahuynh.core_service.service.TypeService;
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
    /**
     * Them type
     * ADMIN
     * Type Response
     */
    @PostMapping("/create")
    public ResponseEntity<?> createType(@RequestBody TypeRequest request) {
        TypeEntity type = typeService.createType(request);
        return new ResponseEntity<>(new ApiResponse("Create Successfully",
                TypeResponse.toResponse(type)), HttpStatus.CREATED);
    }

    /**
     * Xoa type
     * ADMIN
     * message
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteType(@PathVariable(name = "id") Long id) {
        typeService.deleteType(id);
        return new ResponseEntity<>(new MessageResponse("Delete Successfully"), HttpStatus.OK);
    }


    /**
     * Sua type
     * ADMIN
     * TypeResponse
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateType(@PathVariable(name = "id") Long id, @RequestBody TypeRequest request) {
        TypeEntity type = typeService.updateType(id, request);
        return new ResponseEntity<>(new ApiResponse("Update  Successfully",
                TypeResponse.toResponse(type)), HttpStatus.OK);
    }

    /**
     * Lay tat ca type
     * ADMIN - USER
     * List<TypeResponse>
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllType() {
        List<TypeEntity> type = typeService.getAllType();
        return new ResponseEntity<>(new ApiResponse("Successfully", TypeResponse.toResponseList(type)), HttpStatus.OK);
    }

    /**
     * Lay song from type
     * List<SongResponse>
     */
    @GetMapping("/{id}/songs")
    public ResponseEntity<?> getSongFromType(@PathVariable(name = "id") Long id) {
        List<SongEntity> songs = typeService.getSongFromType(id);
        return new ResponseEntity<>(new ApiResponse("Successfully", SongResponse.toResponseList(songs)), HttpStatus.OK);
    }


}
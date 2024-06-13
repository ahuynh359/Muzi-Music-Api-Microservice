package com.ahuynh.core_service.controller;

import com.ahuynh.core_service.model.entity.AlbumEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.rest.request.UpdateAlbumRequest;
import com.ahuynh.core_service.model.rest.request.UpdateSongRequest;
import com.ahuynh.core_service.model.rest.response.AlbumResponse;
import com.ahuynh.core_service.model.rest.response.ApiResponse;
import com.ahuynh.core_service.model.rest.response.MessageResponse;
import com.ahuynh.core_service.model.rest.response.SongResponse;
import com.ahuynh.core_service.service.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final ObjectMapper objectMapper;

    /**
     * Thêm Album
     * ADMIN
     * AlbumResponse
     */

    @PostMapping("/create")
    public ResponseEntity<?> createAlbum(@RequestParam("avatar") MultipartFile avatar,
                                         @RequestParam("name") String name
    ) {

        return new ResponseEntity<>
                (new ApiResponse("Create Successfully",
                        albumService.createAlbum(avatar, name)), HttpStatus.CREATED);
    }

    /**
     * Lấy hết album
     * ADMIN - USER
     * AlbumResponse
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllAlbum() {
        List<AlbumEntity> album = albumService.getAllAlbum();
        List<AlbumResponse> responses = AlbumResponse.toResponseList(album);
        return new ResponseEntity<>(new ApiResponse("Successfully", responses), HttpStatus.OK);
    }


    /**
     * Lấy song từ album theo id
     * ADMIN - USER
     * AlbumResponse
     */
    @GetMapping("/{id}/songs")
    public ResponseEntity<?> getSongFromAlbum(@PathVariable(name = "id") Long id) {
        List<SongEntity> songs = albumService.getSongFromAlbum(id);
        List<SongResponse> responses = SongResponse.toResponseList(songs);
        return new ResponseEntity<>(new ApiResponse("Successfully",
                responses), HttpStatus.OK);
    }

    /**
     * Delete Alum
     * ADMIN
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable(name = "id") Long id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(new MessageResponse("Delete Successfully"), HttpStatus.OK);
    }


    /**
     * Update Alum
     * ADMIN
     */
    @PutMapping("/change")
    public ResponseEntity<?> updateAlbum(@RequestBody UpdateAlbumRequest request) {
        return new ResponseEntity<>(new ApiResponse("Update Successfully",
                albumService.updateAlbum(request)), HttpStatus.OK);
    }



}
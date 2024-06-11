package com.ahuynh.core_service.controller;

import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.rest.response.ApiResponse;
import com.ahuynh.core_service.model.rest.response.SongResponse;
import com.ahuynh.core_service.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/song")
public class SongController {
    private final SongService songService;

    /**
     * Thêm bài hát
     * ADMIN
     * SongResponse
     */

    @PostMapping("/create")
    public ResponseEntity<?> createSong(@RequestParam("name") String name,
                                        @RequestParam("avatar") MultipartFile avatar,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("lyrics") String lyrics,
                                        @RequestParam("albumId") Long albumId,
                                        @RequestParam("singer") String singer) {

        return new ResponseEntity<>(new ApiResponse("Create Successfully",
                songService.createSong(name, avatar, file, lyrics, albumId, singer)), HttpStatus.CREATED);
    }

    /**
     * Lấy song by id
     * USER - ADMIN
     */

    @GetMapping("{id}/songs")
    public ResponseEntity<?> getSongById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new ApiResponse("Successfully", songService.getSongById(id)), HttpStatus.OK);
    }

    /**
     * Lấy het song
     * USER - ADMIN
     */

    @GetMapping("/all")
    public ResponseEntity<?> getAllSong() {
        return new ResponseEntity<>(new ApiResponse("Successfully", songService.getAllSong()), HttpStatus.OK);
    }


//    @GetMapping("/get-type/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getTypeOfSong(@PathVariable(name = "id") Long id) {
//        List<Type> type = songService.getTypeOfSong(id);
//        List<TypeResponse> responses = TypeResponse.toResponseList(type);
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully", responses), HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> deleteSong(@PathVariable(name = "id") Long id) {
//        songService.deleteSong(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Delete song Successfully", ""), HttpStatus.OK);
//    }
//
//    @PutMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> updateSong(@PathVariable(name = "id") Long id, @RequestBody UpdateSongRequest newSong) {
//        Song song = songService.updateSong(id, newSong);
//        return new ResponseEntity<>(new ApiResponse(true, "Update song Successfully",
//                objectMapper.convertValue(song, SongResponse.class)), HttpStatus.OK);
//    }


}

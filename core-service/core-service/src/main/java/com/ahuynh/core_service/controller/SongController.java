package com.ahuynh.core_service.controller;

import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.rest.request.UpdateSongRequest;
import com.ahuynh.core_service.model.rest.response.ApiResponse;
import com.ahuynh.core_service.model.rest.response.MessageResponse;
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
     * Thêm bài hát- chua co type
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
                SongResponse.toResponse(songService.createSong(name, avatar, file, lyrics, albumId, singer))), HttpStatus.CREATED);
    }

    /**
     * Lấy song by id
     * USER - ADMIN
     * SongResponse
     */

    @GetMapping("{id}")
    public ResponseEntity<?> getSongById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(SongResponse.toResponse(songService.getSongById(id)));
    }

    /**
     * Lấy song by id
     * USER - ADMIN
     * SongResponse
     */

    @GetMapping("/list")
    public ResponseEntity<?> getListSongById(@RequestParam(name = "id") List<Long> id) {
        return ResponseEntity.ok(SongResponse.toResponseList(songService.getListSongById(id)));
    }


    /**
     * Lấy het song
     * USER - ADMIN
     * ListSongResponse
     */

    @GetMapping("/all")
    public ResponseEntity<?> getAllSong() {
        return new ResponseEntity<>(new ApiResponse("Successfully", SongResponse.toResponseList(songService.getAllSong())), HttpStatus.OK);
    }

    /**
     * Lấy newest song
     * USER - ADMIN
     * ListSongResponse
     */


    @GetMapping("/top-3")
    public ResponseEntity<?> getTop3Song() {
        return new ResponseEntity<>(new ApiResponse("Successfully", SongResponse.toResponseList(songService.getTop3Song())), HttpStatus.OK);
    }


    /**
     * delete song
     * ADMIN
     * Message
     */

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSong(@PathVariable(name = "id") Long id) {
        songService.deleteSong(id);
        return new ResponseEntity<>(new MessageResponse("Delete song Successfully"), HttpStatus.OK);
    }

    /**
     * update song
     * ADMIN
     * Song Response
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateSong(@PathVariable(name = "id") Long id, @RequestBody UpdateSongRequest request) {
        return new ResponseEntity<>(new ApiResponse("Update song Successfully",
                SongResponse.toResponse(songService.updateSong(id, request))), HttpStatus.OK);
    }

    /**
     * update listen
     * ADMIN
     * Song Response
     */
    @PutMapping("/listen/{id}")
    public ResponseEntity<?> updateListenSong(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new ApiResponse("Update song Successfully",
                SongResponse.toResponse(songService.updateListenSong(id))), HttpStatus.OK);
    }

    /**
     * Search song, account , album
     * USER - ADMIN
     * ListSongResponse
     */

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String str) {
        return new ResponseEntity<>(new ApiResponse("Successfully", songService.search(str)), HttpStatus.OK);
    }
}

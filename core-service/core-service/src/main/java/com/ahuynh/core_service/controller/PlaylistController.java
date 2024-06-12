package com.ahuynh.core_service.controller;

import com.ahuynh.core_service.model.entity.PlaylistEntity;
import com.ahuynh.core_service.model.rest.request.PlaylistRequest;
import com.ahuynh.core_service.model.rest.response.ApiResponse;
import com.ahuynh.core_service.model.rest.response.MessageResponse;
import com.ahuynh.core_service.model.rest.response.PlaylistResponse;
import com.ahuynh.core_service.model.rest.response.SongResponse;
import com.ahuynh.core_service.service.FeignClientUser;
import com.ahuynh.core_service.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAllPlaylistByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse("Successfully", PlaylistResponse.toResponseList(
                playlistService.getAllPlaylist(id))), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPlaylist(@RequestBody PlaylistRequest playlistRequest) {
        return new ResponseEntity<>(new ApiResponse("Successfully",
                PlaylistResponse.toResponse(playlistService.createPlaylist(playlistRequest))), HttpStatus.OK);


    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable(name = "id") Long id) {
        playlistService.deletePlaylist(id);
        return new ResponseEntity<>(new MessageResponse("Delete  Successfully"), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlaylist(@PathVariable(name = "id") Long id, @RequestBody PlaylistRequest playlistRequest) {
        PlaylistEntity playlist = playlistService.updatePlaylist(id, playlistRequest);
        return new ResponseEntity<>(new ApiResponse( "Update  Successfully", PlaylistResponse.toResponse(playlist)), HttpStatus.OK);
    }


    @PostMapping("/{playlistId}/add/{songId}")
    public ResponseEntity<?> addSongToPlaylist(@PathVariable(name = "playlistId") Long playlistId,@PathVariable(name = "songId") Long songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
        return new ResponseEntity<>(new MessageResponse("Success"), HttpStatus.CREATED);
    }

    @PostMapping("/{playlistId}/remove/{songId}")
    public ResponseEntity<?> removeSongToPlaylist(@PathVariable(name = "playlistId") Long playlistId,@PathVariable(name = "songId") Long songId) {
        playlistService.removeSongFromPlaylist(playlistId, songId);
        return new ResponseEntity<>(new MessageResponse("Success"), HttpStatus.CREATED);
    }


    @GetMapping("/{id}/get/song")
    public ResponseEntity<?> getAllSongFromPlaylist(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new ApiResponse("Successfully", SongResponse.toResponseList(playlistService.getAllSongFromPlaylist(id))), HttpStatus.OK);
    }


}
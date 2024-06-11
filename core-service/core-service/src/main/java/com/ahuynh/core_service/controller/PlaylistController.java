package com.ahuynh.core_service.controller;

import com.ahuynh.core_service.service.FeignClientUser;
import com.ahuynh.core_service.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    //private final PlaylistService playlistService;
    private final FeignClientUser feignClientUser;

    @GetMapping()
    public ResponseEntity<?> addPlaylist() {

      return ResponseEntity.ok(feignClientUser.getUser());
    }
//
//    @GetMapping("")
//    public String abc(){
//        return feignClientUser.test();
//    }

//    @GetMapping("/get-by-id/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getPlaylistById(@PathVariable(name = "id") Long id) {
//        Playlist playlist = playlistService.getPlaylistById(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully", objectMapper.convertValue(playlist, PlaylistResponse.class)), HttpStatus.OK);
//    }
//
//    @GetMapping("/get-by-name")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getPlaylistByNameAndUser(@RequestParam(name = "name") String name, @RequestParam(name = "userId") Long userId) {
//        Playlist playlist = playlistService.getPlaylistByNameAndUser(name, userId);
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully", objectMapper.convertValue(playlist, PlaylistResponse.class)), HttpStatus.OK);
//    }
//
//    @GetMapping("/get-all/{userId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getAllPlaylistByUser(@PathVariable(name = "userId") Long userId) {
//        List<Playlist> playlist = playlistService.getAllPlaylistByUser(userId);
//        List<PlaylistResponse> responses = PlaylistResponse.toResponseList(playlist);
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully", responses), HttpStatus.OK);
//    }
//
//
//    //Ko xoa dươc why ?
//    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> deletePlaylistById(@PathVariable(name = "id") Long id) {
//        playlistService.deletePlaylist(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Delete  Successfully", ""), HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> updatePlaylist(@PathVariable(name = "id") Long id, @RequestBody PlaylistRequest playlistRequest) {
//        Playlist playlist = playlistService.updatePlaylist(id, playlistRequest);
//        return new ResponseEntity<>(new ApiResponse(true, "Update  Successfully", objectMapper.convertValue(playlist, PlaylistResponse.class)), HttpStatus.OK);
//    }
//
//
//    @PostMapping("/add-song/{songId}/{playlistId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> addSongToPlaylist(@PathVariable(name = "songId") Long songId, @PathVariable(name = "playlistId") Long playlistId) {
//
//        playlistService.addSongToPlaylist(songId, playlistId);
//        return new ResponseEntity<>(new ApiResponse(true, "  Successfully",
//                ""), HttpStatus.CREATED);
//    }
//
//    @PostMapping("/remove-song/{songId}/{playlistId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> removeSongFromPlaylist(@PathVariable(name = "songId") Long songId, @PathVariable(name = "playlistId") Long playlistId) {
//
//        playlistService.deleteSongFromPlaylist(songId, playlistId);
//        return new ResponseEntity<>(new ApiResponse(true, "  Successfully",
//                ""), HttpStatus.CREATED);
//    }
//
//
//    @GetMapping("/get-all-song/{playlistId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getAllSongFromPlaylist(@PathVariable(name = "playlistId") Long playlistId) {
//        List<Song> song = playlistService.getAllSongFromPlaylist(playlistId);
//        List<SongResponse> responses = SongResponse.toResponseList(song);
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully", responses), HttpStatus.OK);
//    }


}
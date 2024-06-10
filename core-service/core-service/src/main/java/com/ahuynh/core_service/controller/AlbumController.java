package com.ahuynh.core_service.controller;

import com.ahuynh.core_service.model.dto.AlbumDto;
import com.ahuynh.core_service.model.dto.SongDto;
import com.ahuynh.core_service.model.entity.AlbumEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.rest.response.ApiResponse;
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
//
//    /**
//     *Thêm Album
//     * ADMIN
//     *AlbumResponse
//     */
//
//    @PostMapping()
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> addAlbum(@RequestParam("avatar") MultipartFile avatar,
//                                      @RequestParam("name") String name,
//                                      @RequestParam("description") String description) {
//
//        Album albumResponse = albumService.save(avatar, name, description);
//        return new ResponseEntity<>
//                (new ApiResponse(true, "Create Successfully",
//                        objectMapper.convertValue(albumResponse, AlbumResponse.class)), HttpStatus.CREATED);
//    }
//
//    /**
//     * Lấy album theo id
//     * ADMIN - USER
//     *AlbumResponse
//     */
//    @GetMapping("/get-by-id/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getAlbumById(@PathVariable Long id) {
//        Album album = albumService.getAlbumById(id);
//        return new ResponseEntity<>(objectMapper.convertValue(album, AlbumResponse.class), HttpStatus.OK);
//    }

    /**
     * Lấy hết album
     * ADMIN - USER
     * AlbumResponse
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllAlbum() {
        List<AlbumEntity> album = albumService.getAllAlbum();
        List<AlbumDto> responses = AlbumDto.toResponseList(album);
        return new ResponseEntity<>(new ApiResponse("Successfully", responses), HttpStatus.OK);
    }
//
//    /**
//     * Lấy album theo name
//     * ADMIN - USER
//     *AlbumResponse
//     */
//    @GetMapping("/get-by-name")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    public ResponseEntity<?> getAlbumByName(@RequestParam String name) {
//        Album album = albumService.getAlbumByName(name);
//        return new ResponseEntity<>(objectMapper.convertValue(album, AlbumResponse.class), HttpStatus.OK);
//    }
//

    /**
     * Lấy song từ album theo id
     * ADMIN - USER
     * AlbumResponse
     */
    @GetMapping("/{id}/songs")
    public ResponseEntity<?> getSongFromAlbum(@PathVariable(name = "id") Long id) {
        List<SongEntity> songs = albumService.getSongFromAlbum(id);
        List<SongDto> responses = SongDto.toDtoList(songs);
        return new ResponseEntity<>(new ApiResponse("Successfully",
                responses), HttpStatus.OK);
    }

//    /**
//     * Delete Alum
//     * ADMIN
//     *
//     */
//    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> deleteAlbum(@PathVariable(name = "id") Long id) {
//        albumService.deleteAlbum(id);
//        return new ResponseEntity<>(new ApiResponse(true, "Delete Successfully", ""), HttpStatus.OK);
//    }
//
//
//    /**
//     * Update Alum
//     * ADMIN
//     *
//     */
//    @PutMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> updateAlbum(@PathVariable(name = "id") Long id, @RequestBody AlbumRequest newAlbum) {
//        Album album = albumService.updateAlbum(id, newAlbum);
//        return new ResponseEntity<>(new ApiResponse(true, "Update Successfully", objectMapper.convertValue(album, AlbumResponse.class)), HttpStatus.OK);
//    }
//
//    /**
//     * Thêm song to album
//     * ADMIN
//     *
//     */
//    @PostMapping("/add-song-to-album")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> addSongToAlbum(@RequestParam("songId") Long songId,
//                                            @RequestParam("albumId") Long albumId
//    ) {
//        albumService.addSongToAlbum(songId, albumId);
//        return new ResponseEntity<>(new ApiResponse(true,
//                "Add Successfully", ""), HttpStatus.OK);
//    }
//    /**
//     * Delete song from album
//     * ADMIN
//     *
//     */
//    @PostMapping("/delete-song-from-album")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> deleteSongFromAlbum(@RequestParam("songId") Long songId,
//                                                 @RequestParam("albumId") Long albumId
//    ) {
//        albumService.deleteSongFromAlbum(songId, albumId);
//        return new ResponseEntity<>(new ApiResponse(true,
//                "Add Successfully", ""), HttpStatus.OK);
//    }


}
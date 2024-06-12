package com.ahuynh.core_service.service;

import com.ahuynh.core_service.exception.EntityNotFoundException;
import com.ahuynh.core_service.exception.ErrorCode;
import com.ahuynh.core_service.exception.InvalidException;
import com.ahuynh.core_service.model.entity.AlbumEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.rest.request.UpdateAlbumRequest;
import com.ahuynh.core_service.model.rest.response.AlbumResponse;
import com.ahuynh.core_service.repository.AlbumRepository;
import com.ahuynh.core_service.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;

    public List<AlbumEntity> getAllAlbum() {
        return albumRepository.findAll();
    }

    //    private final ModelMapper modelMapper;
    private final FirebaseService firebaseService;
//    private final SongRepository songRepository;

//
//    public Album getAlbumById(Long id) {
//        return albumRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Album with id " + id + " not found"));
//    }
//
//    public List<Album> getAlbum() {
//        if (albumRepository.count() == 0) {
//            throw new ResourceNotFoundException("There is no album");
//        }
//        return albumRepository.findAll();
//    }

    public List<SongEntity> getSongFromAlbum(Long id) {
        if (albumRepository.count() == 0) {
            throw new EntityNotFoundException("There is no album");
        }
        albumRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Album not exits id =" + id.toString()));
        return albumRepository.findSongById(id);
    }

    //
//    public void deleteAlbum(Long id) {
//        albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album not exits id =" + id.toString()));
//        albumRepository.deleteById(id);
//    }
//
    public AlbumResponse updateAlbum(UpdateAlbumRequest request) {
        AlbumEntity updatedAlbum = albumRepository.findById(request.getId()).orElseThrow(() ->
                new EntityNotFoundException("Album not exits id =" + request.getId().toString()));
        if (albumRepository.existsByName(request.getName())) {
            throw new InvalidException("Album with name " + request.getName() + " already exists",ErrorCode.ERROR);
        }

        if (request.getName() != null) {
            updatedAlbum.setName(request.getName());
        }


        return AlbumResponse.toResponse(albumRepository.save(updatedAlbum));
    }


    public AlbumResponse createAlbum(MultipartFile avatar, String name) {
        if (albumRepository.existsByName(name)) {
            throw new InvalidException("Album with name " + name + " already exists", ErrorCode.ERROR);
        }
        String url = firebaseService.upload(avatar, "image/png");
        AlbumEntity album = new AlbumEntity(name, url);

        return AlbumResponse.toResponse(albumRepository.save(album));
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
//
//    public Album getAlbumByName(String name) {
//        return albumRepository.findByName(name).orElseThrow(() ->
//                new ResourceNotFoundException("Album with name " + name + " not found"));
//    }
//
//    public void addSongToAlbum(Long songId, Long albumId) {
//        Album album = albumRepository.findById(albumId).orElseThrow(() ->
//                new ResourceNotFoundException("Album with id " + albumId + " not found"));
//        Song song = songRepository.findById(songId).orElseThrow(() ->
//                new ResourceNotFoundException("Song with id " + songId + " not found"));
//        album.addSong(song);
//        albumRepository.save(album);
//    }
//
//    public void deleteSongFromAlbum(Long songId, Long albumId) {
//        Album album = albumRepository.findById(albumId).orElseThrow(() ->
//                new ResourceNotFoundException("Album with id " + albumId + " not found"));
//        Song song = songRepository.findById(songId).orElseThrow(() ->
//                new ResourceNotFoundException("Song with id " + songId + " not found"));
//        album.removeSong(song);
//        albumRepository.save(album);
//    }
}
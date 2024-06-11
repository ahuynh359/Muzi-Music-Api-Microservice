package com.ahuynh.core_service.service;

import com.ahuynh.core_service.exception.EntityNotFoundException;
import com.ahuynh.core_service.model.entity.AlbumEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.rest.request.UpdateSongRequest;
import com.ahuynh.core_service.model.rest.response.SongResponse;
import com.ahuynh.core_service.repository.AlbumRepository;
import com.ahuynh.core_service.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final FirebaseService firebaseService;

    public SongResponse createSong(String name, MultipartFile avatar, MultipartFile file, String lyrics, Long albumIb, String singer) {
        AlbumEntity album = albumRepository.findById(albumIb).orElseThrow(() -> new EntityNotFoundException("No Album with " + albumIb));
        String urlAvatar = firebaseService.upload(avatar, "image/png");
        String urlFile = firebaseService.upload(file, "audio/mpeg");
        SongEntity s = new SongEntity(name, urlAvatar, urlFile, lyrics, album, singer);

        return SongResponse.toResponse(songRepository.save(s));
    }

    public SongEntity getSongById(Long id) {
        return songRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("SongEntity with id " + id + " not found"));
    }

    public void deleteSong(Long id) {
        songRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("SongEntity with id " + id + " not found"));
        songRepository.deleteById(id);
    }

    public SongEntity updateSong(Long id, UpdateSongRequest request) {
        SongEntity song = songRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("SongEntity not exits id =" + id));

        AlbumEntity updatedAlbum =
                albumRepository.findById(request.getAlbumId()).orElseThrow(() ->
                        new EntityNotFoundException("Album not exits id =" + request.getAlbumId().toString()));

        if (request.getName() != null) {
            song.setName(request.getName());
        }
        if (request.getAlbumId() != null) {
            song.setAlbum(updatedAlbum);
        }
        if (request.getLyrics() != null) {
            song.setLyrics(request.getLyrics());
        }
        if (request.getSinger() != null) {
            song.setLyrics(request.getSinger());
        }

        return songRepository.save(song);
    }

    public List<SongResponse> getAllSong() {
        return SongResponse.toResponseList(songRepository.findAll());
    }


}

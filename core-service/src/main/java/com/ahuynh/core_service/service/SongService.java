package com.ahuynh.core_service.service;

import com.ahuynh.core_service.exception.EntityNotFoundException;
import com.ahuynh.core_service.model.entity.AlbumEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.rest.request.NotificationRequest;
import com.ahuynh.core_service.model.rest.request.UpdateSongRequest;
import com.ahuynh.core_service.model.rest.response.AlbumResponse;
import com.ahuynh.core_service.model.rest.response.SearchResponse;
import com.ahuynh.core_service.model.rest.response.SongResponse;
import com.ahuynh.core_service.repository.AlbumRepository;
import com.ahuynh.core_service.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.N;
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
    private final FeignClientUser feignClientUser;
    private final FeignClientNotification feignClientNotification;

    public SongEntity createSong(String name, MultipartFile avatar, MultipartFile file, String lyrics, Long albumIb, String singer) {
        AlbumEntity album = albumRepository.findById(albumIb).orElseThrow(() -> new EntityNotFoundException("No Album with " + albumIb));
        String urlAvatar = firebaseService.upload(avatar, "image/png");
        String urlFile = firebaseService.upload(file, "audio/mpeg");
        NotificationRequest notificationRequest = new NotificationRequest("title","new song is coming " + name, "muzi-music","123456");
        feignClientNotification.sendNotification(notificationRequest);
        return songRepository.save(new SongEntity(name, urlAvatar, urlFile, lyrics, album, singer));
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

    public List<SongEntity> getAllSong() {
        return songRepository.findAll();
    }


    public SongEntity updateListenSong(Long id) {
        SongEntity song = songRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("SongEntity not exits id =" + id));
        song.setListen(song.getListen() + 1);
        return songRepository.save(song);
    }

    public SearchResponse search(String keyword) {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setUsers(feignClientUser.searchUser(keyword));
        searchResponse.setAlbums(AlbumResponse.toResponseList(albumRepository.findByNameContainingIgnoreCase(keyword)));
        searchResponse.setSongs(SongResponse.toResponseList(songRepository.findByNameContainingIgnoreCase(keyword)));
        return searchResponse;
    }



    public List<SongEntity> getTop5Song() {

        return songRepository.findTop5ByOrderByListenDesc();
    }

    public List<SongEntity> getListSongById(List<Long> id) {
        return songRepository.findAllById(id);
    }
}

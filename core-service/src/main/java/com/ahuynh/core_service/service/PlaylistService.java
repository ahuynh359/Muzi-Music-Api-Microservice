package com.ahuynh.core_service.service;


import com.ahuynh.core_service.exception.EntityNotFoundException;
import com.ahuynh.core_service.exception.ErrorCode;
import com.ahuynh.core_service.exception.InvalidException;
import com.ahuynh.core_service.model.entity.PlaylistEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.rest.request.PlaylistRequest;
import com.ahuynh.core_service.model.rest.response.PlaylistResponse;
import com.ahuynh.core_service.model.rest.response.UserResponse;
import com.ahuynh.core_service.repository.PlaylistRepository;
import com.ahuynh.core_service.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final FeignClientUser feignClientUser;
    private final SongRepository songRepository;


    public PlaylistEntity createPlaylist(PlaylistRequest request) {
        UserResponse user = feignClientUser.getUser(request.getUserId());
        if (playlistRepository.existsByName(request.getName())
                && playlistRepository.existsByUserId(request.getUserId())) {
            throw new InvalidException("Playlist with name " + request.getName() + " already exists with user id " + request.getUserId(), ErrorCode.ERROR);
        }

        PlaylistEntity playlist = new PlaylistEntity(request.getName(), request.getUserId());

        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    public PlaylistEntity updatePlaylist(Long id, PlaylistRequest request) {
        PlaylistEntity updatedPlaylist = playlistRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Playlist not exits id =" + id));
        if (playlistRepository.existsByName(request.getName()) && playlistRepository.existsByUserId(request.getUserId())) {
            throw new InvalidException("Playlist with name " + request.getName() + " already exists", ErrorCode.ERROR);
        }

        if (request.getName() != null) {
            updatedPlaylist.setName(request.getName());
        }


        return playlistRepository.save(updatedPlaylist);
    }


    public List<PlaylistEntity> getAllPlaylist(Long id) {
        return playlistRepository.findAllByUserId(id);
    }

    //
//
//    public Playlist getPlaylistByNameAndUser(String name,Long userId) {
//        return playlistRepository.findByNameAndUserId(name,userId).orElseThrow(() ->
//                new ResourceNotFoundException("Playlist with name " + name + " not found"));
//    }
//
//    public Playlist getPlaylistById(Long id) {
//        return playlistRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Playlist with id " + id + " not found"));
//    }
//
//    public void addSongToPlaylist(Long songId, Long playlistId) {
//        Playlist updatedPlaylist = playlistRepository.findById(playlistId).orElseThrow(() ->
//                new ResourceNotFoundException("Playlist not exits id = " + playlistId));
//
//
//        Song updateSong = songRepository.findById(songId).orElseThrow(() ->
//                new ResourceNotFoundException("Song not exits id = " + playlistId));
//
//        updatedPlaylist.addSong(updateSong);
//        songRepository.save(updateSong);
//    }
//
//    public void deleteSongFromPlaylist(Long songId, Long playlistId) {
//        Playlist updatedPlaylist = playlistRepository.findById(playlistId).orElseThrow(() ->
//                new ResourceNotFoundException("Playlist not exits id = " + playlistId));
//
//
//        Song updateSong = songRepository.findById(songId).orElseThrow(() ->
//                new ResourceNotFoundException("Song not exits id = " + playlistId));
//
//        updatedPlaylist.removeSong(updateSong);
//        songRepository.save(updateSong);
//    }
//
    public List<SongEntity> getAllSongFromPlaylist(Long playlistId) {
        PlaylistEntity updatedPlaylist = playlistRepository.findById(playlistId).orElseThrow(() ->
                new EntityNotFoundException("Playlist not exits id = " + playlistId));

        return updatedPlaylist.getSongs();
    }


    public PlaylistEntity addSongToPlaylist(Long playlistId, Long songId) {
        PlaylistEntity updatedPlaylist = playlistRepository.findById(playlistId).orElseThrow(() ->
                new EntityNotFoundException("Playlist not exits id = " + playlistId));
        SongEntity song = songRepository.findById(songId).orElseThrow(() ->
                new EntityNotFoundException("Song not exits id = " + songId));
        updatedPlaylist.addSong(song);
        return playlistRepository.save(updatedPlaylist);
    }

    public PlaylistEntity removeSongFromPlaylist(Long playlistId, Long songId) {
        PlaylistEntity updatedPlaylist = playlistRepository.findById(playlistId).orElseThrow(() ->
                new EntityNotFoundException("Playlist not exits id = " + playlistId));
        SongEntity song = songRepository.findById(songId).orElseThrow(() ->
                new EntityNotFoundException("Song not exits id = " + songId));
        updatedPlaylist.removeSong(song);
        return playlistRepository.save(updatedPlaylist);
    }
}
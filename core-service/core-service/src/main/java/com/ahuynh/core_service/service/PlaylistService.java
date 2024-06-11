package com.ahuynh.core_service.service;


import com.ahuynh.core_service.exception.EntityNotFoundException;
import com.ahuynh.core_service.exception.ErrorCode;
import com.ahuynh.core_service.exception.InvalidException;
import com.ahuynh.core_service.model.entity.PlaylistEntity;
import com.ahuynh.core_service.model.rest.request.PlaylistRequest;
import com.ahuynh.core_service.model.rest.response.PlaylistResponse;
import com.ahuynh.core_service.model.rest.response.UserResponse;
import com.ahuynh.core_service.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final FeignClientUser feignClientUser;

    public String abc() {
        return feignClientUser.test();
    }


    public PlaylistResponse addPlaylist(PlaylistRequest request) {
        UserResponse user = feignClientUser.getUser();
        if (playlistRepository.existsByName(request.getName())
                && playlistRepository.existsByUserId(request.getUserId())) {
            throw new InvalidException("Playlist with name " + request.getName() + " already exists with user id " + request.getUserId(), ErrorCode.ERROR);
        }


        PlaylistEntity playlist = new PlaylistEntity(request.getName(), user.getId());

        return PlaylistResponse.toResponse(playlistRepository.save(playlist));
    }

    public void deletePlaylist(Long id) {
        PlaylistEntity playlist = playlistRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Playlist not exits id =" + id));
        playlistRepository.delete(playlist);
    }

    public PlaylistResponse updatePlaylist(Long id, PlaylistRequest newPlaylist) {
        PlaylistEntity updatedPlaylist = playlistRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Playlist not exits id =" + id));
        if (playlistRepository.existsByName(newPlaylist.getName())) {
            throw new InvalidException("Playlist with name " + newPlaylist.getName() + " already exists", ErrorCode.ERROR);
        }

        if (newPlaylist.getName() != null) {
            updatedPlaylist.setName(newPlaylist.getName());
        }


        return PlaylistResponse.toResponse(playlistRepository.save(updatedPlaylist));
    }


//
//    public List<Playlist> getAllPlaylistByUser(Long userId) {
//        return playlistRepository.findAllByUserId(userId);
//    }
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
//    public List<Song> getAllSongFromPlaylist(Long playlistId) {
//        Playlist updatedPlaylist = playlistRepository.findById(playlistId).orElseThrow(() ->
//                new ResourceNotFoundException("Playlist not exits id = " + playlistId));
//
//        return updatedPlaylist.getSongs();
//    }
}
package com.ahuynh.core_service.service;


import com.ahuynh.core_service.model.dto.PlaylistDto;
import com.ahuynh.core_service.model.dto.UserDto;
import com.ahuynh.core_service.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final FeignClientUser feignClientUser;

    public String abc(){
        return feignClientUser.test();
    }


    public UserDto addPlaylist(PlaylistDto request) {
        UserDto user = feignClientUser.getUser();
//        if (playlistRepository.existsByName(playlistRequest.getName())
//                && playlistRepository.existsByUserId(playlistRequest.getUserId())) {
//            throw new DuplicateException("Playlist with name " + playlistRequest.getName() + " already exists with user id " + playlistRequest.getUserId());
//        }
//        ;
//
//        Playlist playlist = new Playlist(playlistRequest.getName(), user);
//        user.addPlaylist(playlist);
//
//        userRepository.save(user);
        return user;
    }
//    public void deletePlaylist(Long id) {
//        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist not exits id =" + id));
//        playlistRepository.delete(playlist);
//    }
//
//    public Playlist updatePlaylist(Long iid, PlaylistRequest newPlaylist) {
//        Playlist updatedPlaylist = playlistRepository.findById(iid).orElseThrow(() ->
//                new ResourceNotFoundException("Playlist not exits id =" + iid));
//        if (playlistRepository.existsByName(newPlaylist.getName())) {
//            throw new DuplicateException("Playlist with name " + newPlaylist.getName() + " already exists");
//        }
//
//        if (newPlaylist.getName() != null) {
//            updatedPlaylist.setName(newPlaylist.getName());
//        }
//
//
//        return playlistRepository.save(updatedPlaylist);
//    }

//

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
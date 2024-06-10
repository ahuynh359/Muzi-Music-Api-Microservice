package com.ahuynh.core_service.service;

import com.ahuynh.core_service.model.dto.SongDto;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.mapper.SongMapper;
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
    private final SongMapper songMapper = new SongMapper();

    public List<SongEntity> getAllSong() {
        return songRepository.findAll();
    }
//    private final UserRepository userRepository;
//    private final ModelMapper modelMapper;
//    private final AlbumRepository albumRepository;
//    private final FirebaseService firebaseService;
//
//    public Song save(String name, MultipartFile avatar, MultipartFile file, String lyrics, Long albumIb, String singer) {
//        Album album = albumRepository.findById(albumIb).orElseThrow(() -> new ResourceNotFoundException("No Album with " + albumIb));
//        String urlAvatar = firebaseService.upload(avatar, "image/png");
//        String urlFile = firebaseService.upload(file, "audio/mpeg");
//        Song s = new Song(name, urlAvatar, urlFile, lyrics, album,singer);
//
//        return songRepository.save(s);
//    }
//
//    public Song getSong(Long id) {
//        return songRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Song with id " + id + " not found"));
//    }
//
//    public void deleteSong(Long id) {
//        songRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Song with id " + id + " not found"));
//        songRepository.deleteById(id);
//    }
//
//    public Song updateSong(Long id, UpdateSongRequest newSong) {
//        Song updatedSong = songRepository.findSongById(id).orElseThrow(()
//                -> new ResourceNotFoundException("Song not exits id =" + id.toString()));
//
//        Album updatedAlbum =
//                albumRepository.findById(newSong.getAlbumId()).orElseThrow(() -> new ResourceNotFoundException("Album not exits id =" + newSong.getAlbumId().toString()));
//
//        if (newSong.getName() != null) {
//            updatedSong.setName(newSong.getName());
//        }
//        if (newSong.getAlbumId() != null) {
//            updatedSong.setAlbum(updatedAlbum);
//        }
//        if (newSong.getLyrics() != null) {
//            updatedSong.setLyrics(newSong.getLyrics());
//        }
//        if (newSong.getSinger() != null) {
//            updatedSong.setLyrics(newSong.getSinger());
//        }
//
//        return songRepository.save(updatedSong);
//    }
//
//    public List<Song> getAllSong() {
//        if (songRepository.count() == 0) {
//            throw new ResourceNotFoundException("There is no song");
//        }
//        return songRepository.findAll();
//    }
//
//
//    public Song updateSongLove(Long userId, Long songId, int love) {
//        Song updateSong = songRepository.findById(songId).orElseThrow(() ->
//                new ResourceNotFoundException("Song with id " + songId + " not found"));
//        User updateUser = userRepository.findById(userId).orElseThrow(() ->
//                new ResourceNotFoundException("User with id " + userId + " not found"));
//
//        if (love == 1)
//            updateUser.addLovedSong(updateSong);
//        else updateUser.removeLovedSong(updateSong);
//        return songRepository.save(updateSong);
//
//
//    }
//
//    public List<Type> getTypeOfSong(Long id) {
//        songRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Song with id " + id + " not found"));
//        return songRepository.findAllTypeById(id).orElseThrow(()
//                -> new ResourceNotFoundException("There is no type in this song " + id.toString()));
//    }


}

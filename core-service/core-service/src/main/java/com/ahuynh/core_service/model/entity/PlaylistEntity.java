package com.ahuynh.core_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
@Getter
@Setter
@NoArgsConstructor
public class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotBlank
    @Column(nullable = false)
    private String name;

    private String avatar = "https://firebasestorage.googleapis.com/v0/b/muzimusic-c2598.appspot.com/o/app%2Fplaylist.png?alt=media&token=aa0448ad-b60d-4f76-b7b5-a83162dd7a10";


    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "song_playlist"
            , joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"))
    private List<SongEntity> songs = new ArrayList<>();


    public PlaylistEntity(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }


    public void addSong(SongEntity song) {
        this.songs.add(song);
    }

    public void removeSong(SongEntity song) {
        this.songs.remove(song);
    }
}
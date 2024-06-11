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
    @Column(nullable = false, unique = true)
    private String name;


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


}
package com.ahuynh.core_service.model.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "album")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    private String avatar;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<SongEntity> songs = new ArrayList<>();


    public AlbumEntity(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;

    }

    public void addSong(SongEntity song) {
        this.songs.add(song);
    }

    public void removeSong(SongEntity song) {
        this.songs.remove(song);
    }


}

package com.ahuynh.core_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    private String name;
    private String avatar;
    private String file;

    @Column(columnDefinition = "TEXT")
    private String lyrics;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private AlbumEntity album;

    private Long listen = 0L;

    @NotBlank
    private String singer;

    @ManyToMany(mappedBy = "songs")
    private Set<TypeEntity> types = new HashSet<>();





    public SongEntity(String name, String avatar, String file, String lyrics, AlbumEntity album, String singer) {
        this.name = name;
        this.avatar = avatar;
        this.file = file;
        this.lyrics = lyrics;
        this.album = album;
        this.singer = singer;


    }


}
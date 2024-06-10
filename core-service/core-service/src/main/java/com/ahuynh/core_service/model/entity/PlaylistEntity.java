package com.ahuynh.core_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    public PlaylistEntity(String name, Long userId) {
        this.name = name;
        this.userId = userId;
    }


}
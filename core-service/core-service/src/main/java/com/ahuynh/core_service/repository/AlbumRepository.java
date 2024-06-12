package com.ahuynh.core_service.repository;

import com.ahuynh.core_service.model.entity.AlbumEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.service.AlbumService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity,Long> {
    @Query("SELECT a.songs from AlbumEntity a where a.id = :id")
    List<SongEntity> findSongById(Long id);

    boolean existsByName(String name);

    List<AlbumEntity> findByNameContainingIgnoreCase(String name);
}

package com.ahuynh.core_service.repository;

import com.ahuynh.core_service.model.entity.AlbumEntity;
import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {
    boolean existsByName(String name);

    Optional<SongEntity> findSongById(Long id);


    @Query(value = "SELECT song.types FROM SongEntity song where song.id = :id")
    Optional<List<TypeEntity>> findAllTypeById(Long id);

    List<SongEntity> findByNameContainingIgnoreCase(String name);



    List<SongEntity> findTop5ByOrderByListenDesc();
}
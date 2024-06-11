package com.ahuynh.core_service.repository;

import com.ahuynh.core_service.model.entity.SongEntity;
import com.ahuynh.core_service.model.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    boolean existsByName(String name);
    @Query("select t.songs from TypeEntity t where t.id = :id")
    List<SongEntity> findSongById(Long id);
}

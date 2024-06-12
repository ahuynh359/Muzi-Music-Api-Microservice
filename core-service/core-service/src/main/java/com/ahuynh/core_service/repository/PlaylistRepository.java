package com.ahuynh.core_service.repository;

import com.ahuynh.core_service.model.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity,Long> {
    boolean existsByName(String name);

    boolean existsByUserId(Long userId);

    List<PlaylistEntity> findAllByUserId(Long id);
}

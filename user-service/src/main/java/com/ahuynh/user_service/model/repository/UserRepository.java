package com.ahuynh.user_service.model.repository;

import com.ahuynh.user_service.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserEntity getByUsernameOrEmail(String userNameOrEmail, String userNameOrEmail1);

    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByUsernameContainingIgnoreCase(String username);
    @Query("SELECT u FROM UserEntity u ORDER BY SIZE(u.followers) DESC LIMIT 10")
    List<UserEntity> findHotUser();
}

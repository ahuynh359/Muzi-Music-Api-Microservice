package com.ahuynh.user_service.model.repository;

import com.ahuynh.user_service.model.entity.UserEntity;
import com.ahuynh.user_service.model.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
    VerificationTokenEntity findByToken(String token);

    VerificationTokenEntity findByUser(UserEntity user);
}

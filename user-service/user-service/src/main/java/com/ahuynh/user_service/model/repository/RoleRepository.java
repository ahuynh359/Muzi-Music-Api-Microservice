package com.ahuynh.user_service.model.repository;

import com.ahuynh.user_service.model.entity.role.RoleEntity;
import com.ahuynh.user_service.model.entity.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

    Optional<RoleEntity> findByName(RoleName roleName);
}

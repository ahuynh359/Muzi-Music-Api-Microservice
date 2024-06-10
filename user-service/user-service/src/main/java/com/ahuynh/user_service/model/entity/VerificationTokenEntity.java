package com.ahuynh.user_service.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "verification_token")
@Getter
@Setter
@NoArgsConstructor
public class VerificationTokenEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @Column(name = "expiration_time", nullable = false, updatable = false)
    private Instant expiryTime = Instant.now().plusSeconds(3600 * 2);

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;


    public VerificationTokenEntity(UserEntity user, String token) {
        this.user = user;
        this.token = token;
    }



}
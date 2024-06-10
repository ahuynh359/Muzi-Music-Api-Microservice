package com.ahuynh.user_service.model.entity;

import com.ahuynh.user_service.model.entity.role.RoleEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 6, max = 120, message = "Password has at least 6 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    private String avatar = "https://firebasestorage.googleapis.com/v0/b/muzimusic-c2598.appspot.com/o/avatar%2Favatar.png?alt=media&token=0f4445e9-50a5-4425-9f7c-6c3012b0bcee";
    private boolean enabled = false;

    private String deviceToken = "";


    //User có nhiều Role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role"
            , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> role = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_follow",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<UserEntity> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    private Set<UserEntity> followers = new HashSet<>();

    public UserEntity(String email, String password, String username, Set<RoleEntity> roles, String avatar, boolean enable) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = roles;
        this.enabled = enable;
        this.avatar = avatar;
    }

    public void addRole(RoleEntity role) {
        this.role.add(role);
    }

}



package com.kt.api_login_svc.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // bigserial 대응
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "password")
    private String password;

    protected User(){
    }

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

package com.example.api_login_svc.dto;

import lombok.Data;

@Data
public class UserResponse {
    
    private Long id;
    private String userId;
    private String email;
    private String name;
    
    public UserResponse(Long id, String userId, String email, String name) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.name = name;
    }
}

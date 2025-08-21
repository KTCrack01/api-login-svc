package com.example.api_login_svc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    
    @NotBlank(message = "Email is required")
    private String userEmail;
    
    @NotBlank(message = "Password is required")
    private String password;
} 

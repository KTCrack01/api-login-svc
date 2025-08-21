package com.example.api_login_svc.controller;

import com.example.api_login_svc.dto.ApiResponse;
import com.example.api_login_svc.dto.LoginRequest;
import com.example.api_login_svc.dto.SignupRequest;
import com.example.api_login_svc.dto.UserResponse;
import com.example.api_login_svc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@Valid @RequestBody LoginRequest request) {
        try {
            UserResponse userResponse = userService.login(request);
            return ResponseEntity.ok(ApiResponse.success("Login successful", userResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponse>> signup(@Valid @RequestBody SignupRequest request) {
        try {
            UserResponse userResponse = userService.signup(request);
            return ResponseEntity.ok(ApiResponse.success("Account created successfully", userResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/check-userid/{userId}")
    public ResponseEntity<ApiResponse<Boolean>> checkUserIdExists(@PathVariable String userId) {
        boolean exists = userService.checkUserIdExists(userId);
        return ResponseEntity.ok(ApiResponse.success("User ID check completed", exists));
    }
    
    @GetMapping("/check-email/{email}")
    public ResponseEntity<ApiResponse<Boolean>> checkEmailExists(@PathVariable String email) {
        boolean exists = userService.checkEmailExists(email);
        return ResponseEntity.ok(ApiResponse.success("Email check completed", exists));
    }
    
    @GetMapping("/validate-email/{email}")
    public ResponseEntity<ApiResponse<Boolean>> validateEmailFormat(@PathVariable String email) {
        boolean isValid = userService.validateEmailFormat(email);
        return ResponseEntity.ok(ApiResponse.success("Email validation completed", isValid));
    }
    
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ApiResponse.success("Login service is running"));
    }
}

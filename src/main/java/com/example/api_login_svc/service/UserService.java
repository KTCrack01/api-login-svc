package com.example.api_login_svc.service;

import com.example.api_login_svc.dto.LoginRequest;
import com.example.api_login_svc.dto.SignupRequest;
import com.example.api_login_svc.dto.UserResponse;
import com.example.api_login_svc.entity.User;
import com.example.api_login_svc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    public UserResponse login(LoginRequest request) {
        // Login with email only
        Optional<User> userOptional = userRepository.findByEmailAndPassword(request.getUserEmail(), request.getPassword());
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserResponse(user.getId(), user.getUserId(), user.getEmail(), user.getName());
        }
        
        throw new RuntimeException("Invalid credentials");
    }
    
    public UserResponse signup(SignupRequest request) {
        // Validate email format
        if (!EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
            throw new RuntimeException("Invalid email format");
        }
        
        // Check if passwords match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        
        // Check if userId already exists
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("User ID already exists");
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Create new user
        User user = new User();
        user.setUserId(request.getUserId());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        
        User savedUser = userRepository.save(user);
        
        return new UserResponse(savedUser.getId(), savedUser.getUserId(), savedUser.getEmail(), savedUser.getName());
    }
    
    public boolean checkUserIdExists(String userId) {
        return userRepository.existsByUserId(userId);
    }
    
    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public boolean validateEmailFormat(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}

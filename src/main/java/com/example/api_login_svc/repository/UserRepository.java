package com.example.api_login_svc.repository;

import com.example.api_login_svc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUserId(String userId);
    
    Optional<User> findByEmail(String email);
    
    boolean existsByUserId(String userId);
    
    boolean existsByEmail(String email);
    
    Optional<User> findByUserIdAndPassword(String userId, String password);
    
    Optional<User> findByEmailAndPassword(String email, String password);
} 

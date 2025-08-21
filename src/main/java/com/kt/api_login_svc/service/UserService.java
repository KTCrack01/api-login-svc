package com.kt.api_login_svc.service;

import com.kt.api_login_svc.dto.LoginRequest;
import com.kt.api_login_svc.dto.SignUpRequest;
import com.kt.api_login_svc.dto.UserResponse;
import com.kt.api_login_svc.entity.User;
import com.kt.api_login_svc.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse signUp(SignUpRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        User saved = userRepository.save(
                User.builder()
                        .email(req.getEmail())
                        .password(passwordEncoder.encode(req.getPassword()))
                        .build()
        );
        return new UserResponse(saved.getId(), saved.getEmail());
    }

    @Transactional(readOnly = true)
    public boolean login(LoginRequest req) {
        return userRepository.findByEmail(req.getEmail())
                .map(u -> passwordEncoder.matches(req.getPassword(), u.getPassword()))
                .orElse(false);
    }
}

package com.kt.api_login_svc.controller;

import com.kt.api_login_svc.dto.LoginRequest;
import com.kt.api_login_svc.dto.LoginResponse;
import com.kt.api_login_svc.dto.SignUpRequest;
import com.kt.api_login_svc.dto.UserResponse;
import com.kt.api_login_svc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    // 로그인(아이디/비번 일치 여부만 반환)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        boolean valid = userService.login(request);
        return ResponseEntity.ok(new LoginResponse(valid));
    }
}

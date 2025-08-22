package com.kt.api_login_svc.controller;

import com.kt.api_login_svc.dto.LoginRequest;
import com.kt.api_login_svc.dto.LoginResponse;
import com.kt.api_login_svc.dto.SignUpRequest;
import com.kt.api_login_svc.dto.UserResponse;
import com.kt.api_login_svc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "사용자 관리 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원가입 성공",
                content = @Content(schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
        @ApiResponse(responseCode = "409", description = "이미 존재하는 이메일")
    })
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(
            @Parameter(description = "회원가입 요청 정보", required = true)
            @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    @Operation(summary = "로그인", description = "사용자 인증을 수행합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 처리 완료",
                content = @Content(schema = @Schema(implementation = LoginResponse.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Parameter(description = "로그인 요청 정보", required = true)
            @RequestBody LoginRequest request) {
        boolean valid = userService.login(request);
        return ResponseEntity.ok(new LoginResponse(valid));
    }
}

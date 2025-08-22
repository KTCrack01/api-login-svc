package com.kt.api_login_svc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원가입 요청 정보")
public class SignUpRequest {
    
    @Schema(description = "사용자 이메일", example = "newuser@example.com", required = true)
    private String email;
    
    @Schema(description = "사용자 비밀번호", example = "newpassword123", required = true)
    private String password;
}


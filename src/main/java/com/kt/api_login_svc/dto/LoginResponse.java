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
@Schema(description = "로그인 결과 응답")
public class LoginResponse {
    
    @Schema(description = "로그인 성공 여부", example = "true")
    private boolean valid;
}

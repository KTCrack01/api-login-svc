package com.kt.api_login_svc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("KT API Login Service")
                        .description("사용자 회원가입 및 로그인 API 서비스")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("KT API Team")
                                .email("api-team@kt.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local server"),
                        new Server().url("https://login-svc-gbg8ephsd6bufnca.koreacentral-01.azurewebsites.net").description("Production server")
                ));
    }
}

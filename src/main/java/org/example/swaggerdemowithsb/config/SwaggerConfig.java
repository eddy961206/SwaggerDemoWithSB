package org.example.swaggerdemowithsb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Swagger UI 페이지 상단에 보여지는 개괄 정보
    private Info apiInfo() {
        return new Info()
                .title("API 문서 제목")
                .description("상세 설명을 여기에 작성합니다.")
                .version("1.0.0")
                .contact(new Contact()
                        .name("개발자 이름")
                        .email("dev@example.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org"));
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .addServersItem(new Server().url("http://localhost:8080"));
    }
}
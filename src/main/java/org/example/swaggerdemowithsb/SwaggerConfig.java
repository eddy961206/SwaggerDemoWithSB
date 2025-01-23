package org.example.swaggerdemowithsb;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
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

    // OpenAPI 문서 정보
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

    // API 그룹화 - SpringDoc의 그룹화 기능
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/api/**")
                .build();
    }
}
package org.example.swaggerdemowithsb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() throws IOException {
        // 1. YAML 파일 읽기
        ClassPathResource resource = new ClassPathResource("openapi.yml");
        String yamlContent = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);

        // 2. YAML 파싱
        return new OpenAPIV3Parser().readContents(yamlContent).getOpenAPI();
    }
}
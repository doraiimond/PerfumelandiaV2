package com.Perfumelandia.OpenApiConfig;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI PerfumelandiaApi(){
        return new OpenAPI()
            .info(new Info()
                .title("APIs - Perfumelandia")
                .description("Documentacion API REST")
                .version("2.0"));
    }
}

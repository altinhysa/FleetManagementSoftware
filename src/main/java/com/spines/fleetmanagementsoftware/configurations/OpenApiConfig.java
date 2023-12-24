package com.spines.fleetmanagementsoftware.configurations;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenApi(){
        return new OpenAPI().components(new Components()).info(new Info().title("Fleet Management Api Doc").version("1.0.0").description("Nothing here"));
    }
}

package com.example.trie_search_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("MongoDB with Trie Data Structure")
                        .description("APIs use for the insert and search word using Trie Algorithm")
                        .version("1.0"));
    }
}
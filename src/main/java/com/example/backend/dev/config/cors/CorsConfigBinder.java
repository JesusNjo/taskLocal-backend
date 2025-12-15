package com.example.backend.dev.config.cors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "global-cors.cors-configurations")
public class CorsConfigBinder {

    private List<String> allowedOrigins;
    private Boolean allowedCredentials;
    private String allowedHeader;
    private List<String> allowedMethods;
    private Integer maxAge;

}

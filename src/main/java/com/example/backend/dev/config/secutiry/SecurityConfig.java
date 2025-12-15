package com.example.backend.dev.config.secutiry;

import com.example.backend.dev.config.filters.LoggingMDCFilter;
import com.example.backend.dev.config.secutiry.converter.JwtAuthConverter;
import com.example.backend.dev.config.secutiry.converter.JwtAuthenticationManagerResolver;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {


    private static final String API_PUBLIC_PATH_PATTERN = "/public/**";
    private static final String ACTUATOR_HEALTH_PATTERN = "/actuator/**";
    private static final String SWAGGER_PATH_PATTERN = "/webjars/swagger-ui/**";
    private static final String SWAGGER_UI_PATH_PATTERN = "/swagger-ui/**";
    private static final String SWAGGER_UI_HTML_PATH_PATTERN = "/swagger-ui.html/**";
    private static final String SWAGGER_API_DOCS_PATH_PATTERN = "/v3/api-docs/**";
    private static final String OAUTH2_PATH_PATTERN = "/v1/oauth/tokens/**";
    private static final String WEBSOCKET_PATH_PATTERN = "/ws/**";

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String ADMIN_JWK_ISSUER_URI;

    @Value("${additional-issuers.customer-issuer-uri}")
    private String CUSTOMER_JWK_ISSUER_URI;

    private final JwtAuthConverter jwtAuthConverter;
    //private final KeycloakLogoutHandler keycloakLogoutHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationManagerResolver<HttpServletRequest> resolver,
                                           LoggingMDCFilter mdcFilter) throws Exception {
        log.info("WebSecurityConfig filterChain");
        // Authorization settings
        http
                .addFilterAt(mdcFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authz ->
                        authz
                                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                .requestMatchers(API_PUBLIC_PATH_PATTERN).permitAll()
                                .requestMatchers(ACTUATOR_HEALTH_PATTERN).permitAll()
                                .requestMatchers(SWAGGER_PATH_PATTERN).permitAll()
                                .requestMatchers(SWAGGER_UI_PATH_PATTERN).permitAll()
                                .requestMatchers(SWAGGER_UI_HTML_PATH_PATTERN).permitAll()
                                .requestMatchers(SWAGGER_API_DOCS_PATH_PATTERN).permitAll()
                                .requestMatchers(OAUTH2_PATH_PATTERN).permitAll()
                                .requestMatchers(WEBSOCKET_PATH_PATTERN).permitAll()
                                .anyRequest().authenticated()
                ).csrf().disable();

        //http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverter);
        http.oauth2ResourceServer(oauth2 ->{
            oauth2.authenticationManagerResolver(resolver);
            //oauth2.jwt().jwtAuthenticationConverter(jwtAuthConverter);
        });
        http.csrf().disable();
        return http.build();
    }


    @Bean
    public AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver() {
        return new JwtAuthenticationManagerResolver(
                Map.of(
                        ADMIN_JWK_ISSUER_URI, buildJwtManager(ADMIN_JWK_ISSUER_URI),
                        CUSTOMER_JWK_ISSUER_URI, buildJwtManager(CUSTOMER_JWK_ISSUER_URI)
                )
        );
    }

    private AuthenticationManager buildJwtManager(String issuerUri) {
        JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuerUri);
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtDecoder);
        provider.setJwtAuthenticationConverter(jwtAuthConverter);
        return provider::authenticate;
    }

}
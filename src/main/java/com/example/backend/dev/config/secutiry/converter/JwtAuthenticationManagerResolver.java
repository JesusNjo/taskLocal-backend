package com.example.backend.dev.config.secutiry.converter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.stereotype.Component;

import java.util.Map;

public class JwtAuthenticationManagerResolver implements AuthenticationManagerResolver<HttpServletRequest> {

    private final Map<String, AuthenticationManager> managers;

    public JwtAuthenticationManagerResolver(Map<String, AuthenticationManager> managers) {
        this.managers = managers;
    }

    @Override
    public AuthenticationManager resolve(HttpServletRequest request) {
        // Spring decodifica el token y obtiene el 'iss' (issuer)
        // Aquí puedes aplicar lógica para resolver el AuthenticationManager según el issuer
        // Este ejemplo asume que el Bearer token ya contiene un claim 'iss'
        // Si no, deberías decodificarlo temporalmente para obtenerlo.

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }


        String token = authHeader.substring(7); // Elimina el "Bearer "

        try {
            // Decodifica el JWT sin verificar la firma (solo para leer los claims)
            String[] parts = token.split("\\.");
            if (parts.length < 2) {
                return null; // Token mal formado
            }

            String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));
            com.fasterxml.jackson.databind.JsonNode payload =
                    new com.fasterxml.jackson.databind.ObjectMapper().readTree(payloadJson);

            // Extrae el issuer ("iss") del payload
            String issuer = payload.has("iss") ? payload.get("iss").asText() : null;
            if (issuer == null) {
                return null; // Sin issuer, no se puede determinar el manager
            }

            // Busca el AuthenticationManager correspondiente al issuer
            AuthenticationManager manager = managers.get(issuer);
            if (manager != null) {
                return manager;
            }

            // Si no se encuentra un manager para ese issuer, podrías:
            // - devolver null (Spring lanzará 401)
            // - o usar uno por defecto
            return null;

        } catch (Exception e) {
            // Si falla el parseo del token, no autenticamos
            return null;
        }
    }
}

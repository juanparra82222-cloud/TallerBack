package com.example.demo.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable) // Desactivamos CSRF porque es una API REST
            .authorizeHttpRequests(auth -> auth
                // ¡IMPORTANTE! Liberamos todas las rutas que el juego necesita para funcionar sin token
                .requestMatchers(
                    "/api/jugadores/login", 
                    "/api/jugadores/registro",
                    "/api/partidas/**",
                    "/api/mejoras/**",
                    "/api/inventarios/**"
                ).permitAll()
                // Todo lo demás requiere estar autenticado
                .anyRequest().authenticated()
            );

        return http.build();
    }

    // Este Bean encriptará las contraseñas, requisito clave de la rúbrica ("Seguridad básica")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración CORS vital para que React (Vercel) pueda comunicarse con Spring Boot (Render)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // Permite que Vercel se conecte sin bloqueos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        // Desactivamos las credenciales para evitar conflictos estrictos de CORS en navegadores al usar "*"
        configuration.setAllowCredentials(false); 
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
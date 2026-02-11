package com.ems.backend.config;

import com.ems.backend.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ✅ CORS ENABLE
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // ❌ CSRF disable (JWT use kar rahe ho)
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // PUBLIC
                        .requestMatchers("/auth/**").permitAll()

                        // USER + ADMIN
                        .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/booking/**").hasAnyRole("USER", "ADMIN")

                        // ADMIN only
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/events/create").hasRole("ADMIN")
                        .requestMatchers("/events/update/**").hasRole("ADMIN")
                        .requestMatchers("/events/delete/**").hasRole("ADMIN")

                        // Logged-in users
                        .requestMatchers("/events/**").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                );

        // JWT filter
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ CORS CONFIGURATION (MAIN FIX 🔥)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

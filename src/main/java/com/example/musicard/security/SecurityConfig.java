package com.example.musicard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.beans.Customizer;

@Configuration

    public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                            .requestMatchers(HttpMethod.GET, "/musicas/**", "/artistas/**").authenticated()
                            .requestMatchers(HttpMethod.POST, "/musicas/**", "/artistas/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/musicas/**", "/artistas/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/musicas/**", "/artistas/**").hasRole("ADMIN")
                            .requestMatchers("/users/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
                    );
            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }


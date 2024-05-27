package com.base.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                request
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/introspect").permitAll()
                        .anyRequest().authenticated()
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

}
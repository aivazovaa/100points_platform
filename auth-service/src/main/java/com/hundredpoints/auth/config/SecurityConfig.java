package com.hundredpoints.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(reg -> reg.requestMatchers("/actuator/**","/v3/api-docs/**","/swagger-ui/**","/api/v1/auth/**").permitAll().anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}

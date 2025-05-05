package org.example.springweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
@EnableWebSecurity
public class BeanConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(security ->

                        security.requestMatchers("/profile").hasRole("ADMIN")
                                .anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)

                .oauth2Login(login ->
                        login.defaultSuccessUrl("/profile", true))

                .build();
    }

    @Bean
    @RequestScope
    public Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
package com.JWT.Token1.JWT.Token1.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JWTRequestFilter jwtRequestFilter ;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity )throws Exception{
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
       // httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/api/v1/user/login","/api/v1/user/create").permitAll()
                .requestMatchers("/api/v1/Admin/AddAdmin").hasRole("ADMIN")
                .requestMatchers("/api/v1/User/AddUser").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated();

        return httpSecurity.build();
    }
}

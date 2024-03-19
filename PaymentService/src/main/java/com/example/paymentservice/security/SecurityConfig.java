//package com.example.paymentservice.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//        @Bean
//        public SecurityFilterChain filteringCriteria(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.cors().disable();
//        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//        return http.build();
//        }
//}

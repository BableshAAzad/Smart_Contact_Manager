package com.bablesh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bablesh.service.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {
    // create user and login using java code with in memory service
    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails user1 =
    // User.withDefaultPasswordEncoder().username("aazad").password("12345").roles("ADMIN",
    // "USER")
    // .build();
    // UserDetails user2 =
    // User.withDefaultPasswordEncoder().username("bablesh").password("12345")
    // .roles("ADMIN", "USER").build();
    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,
    // user2);
    // return inMemoryUserDetailsManager;
    // }
    @Autowired
    private SecurityCustomUserDetailService securityCustomUserDetailService;

    @Bean
    // public AuthenticationProvider authenticationProvider() { or
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // UserDetailsService object pass
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);
        // PasswordEncoder object pass
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

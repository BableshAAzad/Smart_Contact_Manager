package com.bablesh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    @Autowired
    private OAuthAuthenicationSuccessHandler handler;

    // ^ configuration of authentication provider spring security
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // * configuration
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/", "/signup", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        // * form default login page
        // httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.formLogin(formLogin -> {
            // formLogin.loginPage("/login")
            // .loginProcessingUrl("/authenticate");
            // or
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
            // formLogin.failureForwardUrl("/login?error=true");
            // formLogin.defaultSuccessUrl("/");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            // formLogin.failureHandler(new AuthenticationFailureHandler() {
            // @Override
            // public void onAuthenticationFailure(HttpServletRequest request,
            // HttpServletResponse response,
            // AuthenticationException exception) throws IOException, ServletException {
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationFailure'");
            // }
            // });
            // formLogin.successHandler(new AuthenticationSuccessHandler() {
            // @Override
            // public void onAuthenticationSuccess(HttpServletRequest request,
            // HttpServletResponse response,
            // Authentication authentication) throws IOException, ServletException {
            // throw new UnsupportedOperationException("Unimplemented method
            // 'onAuthenticationSuccess'");
            // }
            // });
        });

        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });

        // * for logout page
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
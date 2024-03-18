package org.example.tourmanagement.config;

import org.example.tourmanagement.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserService userService;

    @Bean
    DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizationManagerRequestMatcherRegistry) -> {
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/tours").authenticated()
                    .requestMatchers("/types").authenticated()
                    .requestMatchers("/tours/*").hasRole("USER")
                    .requestMatchers("/tours/*/*").hasRole("USER")
                    .requestMatchers("/types/*/*").hasRole("ADMIN")
                    .requestMatchers("/types/*").hasRole("ADMIN");
        }).formLogin((formLoginCustomizer) ->
                formLoginCustomizer
                        .defaultSuccessUrl("/tours", true) // Chuyển hướng sau khi đăng nhập thành công
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return authenticationProvider;
    }
}

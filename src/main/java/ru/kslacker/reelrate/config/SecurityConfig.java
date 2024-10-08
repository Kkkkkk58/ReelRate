package ru.kslacker.reelrate.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(a ->
                        a.requestMatchers("/api/auth/register").permitAll()
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/graphql").permitAll()
                                .requestMatchers("/actuator", "/actuator/**").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic(httpBasic ->
                        httpBasic
                                .authenticationEntryPoint(
                                        (request, response, authException) ->
                                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                                ))
                .formLogin(formLogin ->
                        formLogin.defaultSuccessUrl("/gui", true))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .build();
    }

}

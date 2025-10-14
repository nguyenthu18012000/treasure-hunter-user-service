package com.TreasureHunter.UserService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private static final List<String> PATHS_NO_AUTH = List.of(
            "/*/login",
            "/auth/register",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "ws"
    );

//    private final JwtTokenVerifier jwtTokenVerifier;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity)
            throws Exception {

        String[] pathsNoAuth = new String[PATHS_NO_AUTH.size()];
        PATHS_NO_AUTH.toArray(pathsNoAuth);

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);

//        httpSecurity
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(pathsNoAuth).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint((requesta, response, authException) -> {
//                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                            response.getWriter().write("Unauthorized: Token missing or invalid");
//                        })
//                );
        return httpSecurity.build();
    }
}

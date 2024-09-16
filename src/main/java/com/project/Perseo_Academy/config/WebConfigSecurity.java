package com.project.Perseo_Academy.config;


import com.project.Perseo_Academy.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebConfigSecurity {

    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/auth").permitAll()
                                .requestMatchers("/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("SUPER_ADMIN", "USER")
                                .requestMatchers("/api/test/admin").hasAnyAuthority("ADMIN", "SUPER_ADMIN")
                                .requestMatchers("/api/courses/get").permitAll()
                                .requestMatchers("/api/courses/post").hasAuthority("SUPER_ADMIN")
                                .requestMatchers("/api/courses/put").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
                                .requestMatchers("/api/courses/delete").hasAuthority("SUPER_ADMIN")
                                .requestMatchers("/api/experiences/get").permitAll()
                                .requestMatchers("/api/experiences/post").hasAuthority("USER")
                                .requestMatchers("/api/experiences/put").hasAuthority("USER")
                                .requestMatchers("/api/experiences/delete").hasAuthority("USER")
                                .requestMatchers("/api/my_courses/get").hasAuthority("USER")
                                .requestMatchers("/api/carts/get").hasAuthority("USER")
                                .requestMatchers("/api/carts/post").hasAuthority("USER")
                                .requestMatchers("/api/carts/delete").hasAuthority("USER")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}

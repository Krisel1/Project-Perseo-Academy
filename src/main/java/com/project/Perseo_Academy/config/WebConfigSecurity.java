package com.project.Perseo_Academy.config;


import com.project.Perseo_Academy.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/test").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/courses").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/courses/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/courses").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/courses/{id}").hasAnyAuthority("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/courses/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/experiences").hasAuthority("USER")
                                .requestMatchers(HttpMethod.POST,"/api/experiences").hasAuthority("USER")
                                .requestMatchers(HttpMethod.PUT,"/api/experiences").hasAuthority("USER")
                                .requestMatchers(HttpMethod.DELETE,"/api/experiences/**").hasAuthority("USER")
                                .requestMatchers(HttpMethod.GET,"/api/my_courses").hasAuthority("USER")
                                .requestMatchers(HttpMethod.GET,"/api/my_courses/{id}").hasAuthority("USER")
                                .requestMatchers(HttpMethod.GET,"/api/carts").hasAuthority("USER")
                                .requestMatchers(HttpMethod.POST,"/api/carts").hasAuthority("USER")
                                .requestMatchers(HttpMethod.DELETE,"/api/carts/**").hasAuthority("USER")
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

package com.project.Perseo_Academy.config;


import com.project.Perseo_Academy.jwt.AuthTokenFilter;
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
public class WebConfigSecurity {

    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    public WebConfigSecurity(AuthenticationProvider authenticationProvider, AuthTokenFilter authTokenFilter) {
        this.authenticationProvider = authenticationProvider;
        this.authTokenFilter = authTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/auth/**", "/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/test").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/courses", "/api/courses/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/courses").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/courses/{id}").hasAnyAuthority("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/courses/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/experiences", "/api/experiences/{id}").hasAuthority("USER")
                                .requestMatchers(HttpMethod.POST,"/api/experiences").hasAuthority("USER")
                                .requestMatchers(HttpMethod.PUT,"/api/experiences/{id}").hasAnyAuthority("USER")
                                .requestMatchers(HttpMethod.DELETE,"/api/experiences/**").hasAuthority("USER")
                                .requestMatchers(HttpMethod.GET,"/api/carts", "/api/carts/{id}").hasAuthority("USER")
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

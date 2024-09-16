package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.dto.request.LoginRequest;
import com.project.Perseo_Academy.dto.request.OAuthLoginRequest;
import com.project.Perseo_Academy.dto.request.RegisterRequest;
import com.project.Perseo_Academy.dto.response.AuthResponse;
import com.project.Perseo_Academy.models.ERole;
import com.project.Perseo_Academy.models.User;
import com.project.Perseo_Academy.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RestTemplate restTemplate;

    @Value("${linkedin.api.url}")
    private String linkedinApiUrl;

    @Value("${github.api.url}")
    private String githubApiUrl;

    public AuthResponse login(LoginRequest login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.getTokenService(userDetails);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest register) {
        User user = User.builder()
                .username(register.getUsername())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(register.getRole())
                .build();

        iUserRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getTokenService(user))
                .role(register.getRole())
                .build();
    }

    public AuthResponse loginLinkedin(OAuthLoginRequest request) {
        try {
            OAuth2User linkedInUser = fetchUserFromLinkedIn(request.getAccessToken());
            String email = (String) linkedInUser.getAttribute("email");
            String username = (String) linkedInUser.getAttribute("localizedFirstName");

            User user = iUserRepository.findByEmail(email)
                    .orElseGet(() -> {
                        User newUser = createNewSocialUser(username, email, "linkedin-default");
                        return iUserRepository.save(newUser);
                    });

            String token = jwtService.getTokenService(user);

            return AuthResponse.builder()
                    .token(token)
                    .role(user.getRole())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("LinkedIn authentication failed", e);
        }
    }

    public AuthResponse loginGithub(OAuthLoginRequest request) {
        try {
            OAuth2User githubUser = fetchUserFromGithub(request.getAccessToken());
            String email = (String) githubUser.getAttribute("email");
            String username = (String) githubUser.getAttribute("login");

            User user = iUserRepository.findByEmail(email)
                    .orElseGet(() -> {
                        User newUser = createNewSocialUser(username, email, "github-default");
                        return iUserRepository.save(newUser);
                    });

            String token = jwtService.getTokenService(user);

            return AuthResponse.builder()
                    .token(token)
                    .role(user.getRole())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("GitHub authentication failed", e);
        }
    }

    private OAuth2User fetchUserFromLinkedIn(String accessToken) {
        String url = linkedinApiUrl + "/me?projection=(id,firstName,lastName,emailAddress)";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);

        Map<String, Object> attributes = response.getBody();
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes,
                "id");
    }

    private OAuth2User fetchUserFromGithub(String accessToken) {
        String url = githubApiUrl + "/user";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);

        Map<String, Object> attributes = response.getBody();
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes,
                "login");
    }

    private User createNewSocialUser(String username, String email, String defaultPassword) {
        return User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(defaultPassword))
                .role(ERole.USER)
                .build();
    }
}




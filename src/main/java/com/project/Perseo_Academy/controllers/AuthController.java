package com.project.Perseo_Academy.controllers;

import com.project.Perseo_Academy.dto.request.LoginRequest;
import com.project.Perseo_Academy.dto.request.OAuthLoginRequest;
import com.project.Perseo_Academy.dto.request.RegisterRequest;
import com.project.Perseo_Academy.dto.response.AuthResponse;
import com.project.Perseo_Academy.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/linkedin")
    public ResponseEntity<AuthResponse> loginLinkedin(@RequestBody OAuthLoginRequest request) {
        AuthResponse response = authService.loginLinkedin(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/github")
    public ResponseEntity<AuthResponse> loginGithub(@RequestBody OAuthLoginRequest request) {
        AuthResponse response = authService.loginGithub(request);
        return ResponseEntity.ok(response);
    }

}

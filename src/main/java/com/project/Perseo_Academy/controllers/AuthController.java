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
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

//    @PostMapping("/login/linkedin")
//    public ResponseEntity<AuthResponse> loginLinkedin(@RequestBody OAuthLoginRequest request) {
//        return ResponseEntity.ok(authService.loginLinkedin(request));
//    }
//
//    @PostMapping("/login/github")
//    public ResponseEntity<AuthResponse> loginGithub(@RequestBody OAuthLoginRequest request) {
//        return ResponseEntity.ok(authService.loginGithub(request));
//    }
//
//    @PostMapping("/register/linkedin")
//    public ResponseEntity<AuthResponse> registerLinkedin(@RequestBody OAuthLoginRequest request) {
//        return ResponseEntity.ok(authService.registerlinkedin(request));
//    }
//
//    @PostMapping("/register/github")
//    public ResponseEntity<AuthResponse> registerGithub(@RequestBody OAuthLoginRequest request) {
//        return ResponseEntity.ok(authService.registerGithub(request));
//    }

}

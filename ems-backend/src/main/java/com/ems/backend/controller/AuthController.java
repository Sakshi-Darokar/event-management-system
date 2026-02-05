package com.ems.backend.controller;

import com.ems.backend.dto.*;
import com.ems.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        String msg = authService.register(request);
        return ResponseEntity.ok(new AuthResponse(msg));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String msg = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(msg));
    }
}

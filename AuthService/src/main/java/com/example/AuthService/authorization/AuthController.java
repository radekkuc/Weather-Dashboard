package com.example.AuthService.authorization;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody AuthRequest credentials) {
        return ResponseEntity.ok(authService.register(credentials.getUsername(), credentials.getPassword()));
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody AuthRequest credentials) {
        return ResponseEntity.ok(authService.login(credentials.getUsername(), credentials.getPassword()));
    }
}

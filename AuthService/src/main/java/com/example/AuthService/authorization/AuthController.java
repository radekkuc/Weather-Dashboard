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

    // Returning map is equal to returning json which helps in the future
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody AuthRequest credentials) {
        return ResponseEntity.ok(Map.of(
                "Message", authService.register(credentials.getUsername(), credentials.getPassword())));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest credentials) {
        return ResponseEntity.ok(Map.of(
                "Token", authService.login(credentials.getUsername(), credentials.getPassword())));
    }
}

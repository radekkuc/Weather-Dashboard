package com.example.AuthService.authorization;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody AuthRequest credentials) {


    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody AuthRequest credentials) {


    }
}

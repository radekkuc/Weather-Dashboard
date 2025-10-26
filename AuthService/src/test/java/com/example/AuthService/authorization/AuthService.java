package com.example.AuthService.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void register(String username, String password) {

    }

    public String login(String username, String password) {
        // returns String because it will return token which is a String
    }
}

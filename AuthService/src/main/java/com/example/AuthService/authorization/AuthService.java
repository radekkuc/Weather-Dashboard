package com.example.AuthService.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username, String password) {
        User user = new User(username, password);
        user.setRole("User");
        authRepository.save(user);
    }

    public String login(String username, String password) {
        // returns String because it will return token which is a String
        User user = authRepository.findByUsernameAndPassword(username, password);


    }
}

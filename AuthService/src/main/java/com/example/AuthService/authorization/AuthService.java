package com.example.AuthService.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final UserInfoService userInfoService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(JwtService jwtService, UserInfoService userInfoService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userInfoService = userInfoService;
        this.authenticationManager = authenticationManager;
    }

    public String register(String username, String password) {
        userInfoService.addUser(username, password);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        // Uses UserDetailService
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(username);
        }
        else {
            throw new RuntimeException("Username can not be found");
        }
    }
}

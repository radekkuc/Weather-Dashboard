package com.example.AuthService.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInfoService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(String username, String password) {
        User user = new User(username, password);
        user.setRole("User");

        if(authRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        authRepository.save(user);
    }

    // UserDetails is an interface which represents one authenticated user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User does not exist"));
        return new UserInfoDetails(user);
    }
}

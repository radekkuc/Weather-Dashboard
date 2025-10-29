package com.example.AuthService.authorization;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final String super_secret_key;

    @Autowired
    public JwtService(@Value("${jwt.secret}") String super_secret_key) {
        this.super_secret_key = super_secret_key;
    }

    public String generateToken(String username) {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey())
                .compact();
    }

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(super_secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername() {
        return "";
    }

    public boolean verifyToken() {
        return false;
    }
}

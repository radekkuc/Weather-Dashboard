package com.example.profileService.profile.authorization;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final String super_secret_key;

    @Autowired
    public JwtService(@Value("${jwt.secret}") String super_secret_key) {
        this.super_secret_key = super_secret_key;
    }


    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(super_secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder() // Parses token
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch(JwtException | IllegalArgumentException e) {
            return false;
        }
    }










}

package com.spring.ReactSpringbootERS.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.ReactSpringbootERS.Entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final Set<String> invalidTokens = new HashSet<>();

    public void invalidateToken(String token) {
        invalidTokens.add(token);
    }

    public boolean isTokenInvalid(String token) {
        return invalidTokens.contains(token);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    @SuppressWarnings("deprecation")
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("id", user.getUserId())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(getSigningKey())
                .compact();
    }

    public User decodeToken(String token) {
        @SuppressWarnings("deprecation")
        var claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

                User user = new User();
                user.setUserId(claims.get("id", Integer.class));
                user.setEmail(claims.get("email", String.class));
            
                return user;
    }
}
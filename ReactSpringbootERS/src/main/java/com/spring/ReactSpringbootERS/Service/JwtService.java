package com.spring.ReactSpringbootERS.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.ReactSpringbootERS.Entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    /**
     * Generates a JWT token for the specified user.
     *
     * @param user the user for whom the token is to be generated
     * @return a JWT token as a String
     */
    @SuppressWarnings("deprecation")
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("id", user.getUserId())
                .claim("email", user.getEmail())
                // Add other fields
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) // 15 minutes
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Decodes the given JWT token and retrieves the subject (email) from it.
     *
     * @param token the JWT token to decode
     * @return the subject (email) contained in the token
     * @throws io.jsonwebtoken.JwtException if the token is invalid or expired
     */
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
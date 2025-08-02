package com.invest.Trading.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.util.Date;

import java.util.stream.Collectors;



@Component
public class JwtProvider {

    private final SecretKey key;

    public JwtProvider(@Value("${jwt.secret.key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Authentication auth) {
        String roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
    }

    public String getEmailFromJwtToken(String jwt) {
        String token = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("email", String.class);
    }
}
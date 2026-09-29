package com.hospitalmanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String secretKey;

    private static final long JWT_EXPIRATION =
            1000L * 60 * 60;


    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)

        );
    }


    public String generateToken(String username) {

        return Jwts.builder()

                .subject(username)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + JWT_EXPIRATION
                        )
                )

                .signWith(getSigningKey())

                .compact();
    }


    public String extractUsername(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }


    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        Claims claims =
                extractAllClaims(token);

        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(
            String token) {

        return Jwts.parser()

                .verifyWith(getSigningKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }


    public boolean isTokenValid(
            String token,
            String username) {

        String extractedUsername =
                extractUsername(token);

        return extractedUsername.equals(username)
                && !isTokenExpired(token);
    }


    private boolean isTokenExpired(
            String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        ).before(new Date());
    }
}
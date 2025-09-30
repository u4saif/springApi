package com.codewithsaif.store.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${spring.jwt.secret}")
    private String secret;

    public String generateToken(String email, String tokenType){
        final long tokenExpiration = 60L;
        return getToken(email, tokenExpiration,tokenType);
    }

    private String getToken(String email, long tokenExpiration, String tokenType) {
        return Jwts.builder()
                .subject(email)
                .claim("type",tokenType)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public boolean validateToken(String token){
        if(isAccessToken(token)) {
            try {
                var claims = getClaims(token);
                return claims.getExpiration().after(new Date());
            } catch (JwtException jwtexp) {
                return false;
            }
        } else {
            return false;
        }
    }

    private Claims getClaims(String token) {
        var claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

    public String getEmailId(String token){
        var claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isAccessToken(String token){
        try{
            return "access".equals(getClaims(token).get("type",String.class));
        }catch (JwtException jwtexp){
            return false;
        }
    }
}

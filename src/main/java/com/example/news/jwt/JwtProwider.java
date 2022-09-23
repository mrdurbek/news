
package com.example.news.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProwider {
private final String secret="kalitso'z";

    public String generateToken(String username){
        long expireTime = 36_000_000L;
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ expireTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public String usernameFromToken(String token){
        try {
            String subject = Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return subject;
        }catch (Exception e){
            return null;
        }
    }
}

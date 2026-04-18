package com.anupamchaubey.TaskManagerAPI.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private SecretKey getSigningKey(){
        // This creates a proper HMAC-SHA key from your string
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L*60*60*24*30))
                // FIX: Use getSigningKey() here too!
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Correct
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            System.out.println("JWT Extraction Failed: " + e.getMessage());
            return null;
        }
    }

    private boolean isTokenExpired(String token){
        // FIX: Use parserBuilder() and getSigningKey() here too!
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

    //validate token
    public boolean validateToken(String token, UserDetails userDetails){
        try{
            if(userDetails==null){
                return false;
            }
            final String username = extractUsername(token);
            return (username!=null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }catch(Exception e){
            return false;
        }
    }
}

package com.anupamchaubey.TaskManagerAPI.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    //token generate
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*30))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    //extract username
    public String extractUsername(String token){
        try{
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch(Exception e){
            return null;
        }
    }
    //check if token expired
    private boolean isTokenExpired(String token){
        Date expiration=Jwts.parser()
                .setSigningKey(SECRET_KEY)
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

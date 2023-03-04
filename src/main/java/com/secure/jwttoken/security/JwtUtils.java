package com.secure.jwttoken.security;

import com.secure.jwttoken.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.jwtExpirationMs}")
    private long jwtExpirationMs;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(User userPrincipal){
        return generateTokenByUsername(userPrincipal.getUsername());
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    private String generateTokenByUsername(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            log.error("Invalid JWT Signature: {}",e.getMessage());
        }catch (MalformedJwtException e){
            log.error("Invalid JWT Token: {}",e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("JWT token is expired: {}",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("JWT token is unsupported: {}",e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("JWT claims string is empty {}",e.getMessage());
        }
        return false;
    }

    private Key getSigningKey() {
        byte [] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

package com.secure.jwttoken.cache;

import com.secure.jwttoken.security.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BlackListService {


    private final CacheService cacheService;
    private final JwtUtils jwtUtils;




    public void blackListTokenIfExpired(String token){
     Claims claims = jwtUtils.extractClaims(token);
     Date expiration = claims.getExpiration();
     long expiresIn = expiration.getTime() - new Date().getTime();
     if (expiresIn < 0) cacheService.add(token,- expiresIn / 1000);
    }

    public void validateToken(String token){
        if(cacheService.isBlacklisted(token)){
            throw  new JwtException("Token is blacklisted");
        }
    }






}

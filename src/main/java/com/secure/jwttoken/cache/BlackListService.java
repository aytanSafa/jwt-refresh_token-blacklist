package com.secure.jwttoken.cache;

import com.secure.jwttoken.exceptions.BlacklistException;
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

    public void saveBlackListToken(String token,boolean isBlackList){
     Claims claims = jwtUtils.extractClaims(token);
     Date expiration = claims.getExpiration();
     cacheService.add(token,isBlackList, expiration.getTime() / 1000);
    }

    public boolean validateToken(String token){
        if(cacheService.isBlacklisted(token)){
            throw  new BlacklistException("Token is blacklisted");
        }
        return true;
    }
}

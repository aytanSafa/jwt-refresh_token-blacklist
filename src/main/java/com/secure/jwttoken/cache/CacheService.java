package com.secure.jwttoken.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisTemplate<String,Object> redisTemplate;

    public void add(String token,boolean isBlackList,long expiresIn){
        redisTemplate.opsForValue().set(token,isBlackList,expiresIn, TimeUnit.SECONDS);
    }
    public boolean isBlacklisted(String token){
        return  (Boolean) redisTemplate.opsForValue().get(token) == true;
    }

    public void remove(String token){
        redisTemplate.delete(token);
    }



}

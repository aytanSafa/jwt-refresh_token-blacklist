package com.secure.jwttoken.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BlackListService {

    private final RedisTemplate<String,Object> redisTemplate;

    public void add(String token,long expiresIn){
        redisTemplate.opsForValue().set(token,"",expiresIn, TimeUnit.SECONDS);
    }
    public boolean isBlacklisted(String token){
        return redisTemplate.hasKey(token);
    }

    public void remove(String token){
        redisTemplate.delete(token);
    }



}

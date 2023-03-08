package com.secure.jwttoken.service;

import com.secure.jwttoken.cache.BlackListService;
import com.secure.jwttoken.payload.response.LogoutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final BlackListService blackListService;
    public Object logout(String token) {
        blackListService.saveBlackListToken(token);
        return new LogoutResponse("Logout Success");
    }
}

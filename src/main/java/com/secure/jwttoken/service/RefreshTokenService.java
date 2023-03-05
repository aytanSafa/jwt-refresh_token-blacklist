package com.secure.jwttoken.service;

import com.secure.jwttoken.entity.RefreshToken;
import com.secure.jwttoken.exceptions.RefreshTokenException;
import com.secure.jwttoken.repository.RefreshTokenRepository;
import com.secure.jwttoken.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    @Value("${jwt.jwtRefreshExpirationMs}")
    private long jwtRefreshTokenExpirationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }
    public RefreshToken createRefreshToken(Long userId){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshTokenExpirationMs));
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration (RefreshToken refreshToken){
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException(refreshToken.getToken(),"Refresh token was expired, Please make a new sign in request");
        }
        return refreshToken;
    }


}

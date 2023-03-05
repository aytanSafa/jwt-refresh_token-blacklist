package com.secure.jwttoken.service;

import com.secure.jwttoken.entity.RefreshToken;
import com.secure.jwttoken.payload.request.LoginRequest;
import com.secure.jwttoken.payload.response.LoginResponse;
import com.secure.jwttoken.repository.UserRepository;
import com.secure.jwttoken.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;


    public LoginResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).get();
        String jwt = jwtUtils.generateToken(user);
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return new LoginResponse(jwt,"Bearer",refreshToken.getToken(),user.getId(),user.getUsername(),user.getEmail(),roles);
    }







}

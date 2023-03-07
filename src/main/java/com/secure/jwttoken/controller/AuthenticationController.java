package com.secure.jwttoken.controller;


import com.secure.jwttoken.payload.request.LoginRequest;
import com.secure.jwttoken.payload.request.RefreshTokenRequest;
import com.secure.jwttoken.payload.request.RegisterRequest;
import com.secure.jwttoken.service.LoginService;
import com.secure.jwttoken.service.RefreshTokenService;
import com.secure.jwttoken.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(registerService.saveNewUser(request));
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(loginService.authenticate(loginRequest));
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> generateRefreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(refreshTokenService.getRefreshTokenResponse(refreshTokenRequest));
    }


}

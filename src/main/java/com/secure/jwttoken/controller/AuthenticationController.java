package com.secure.jwttoken.controller;


import com.secure.jwttoken.payload.request.LoginRequest;
import com.secure.jwttoken.payload.request.RefreshTokenRequest;
import com.secure.jwttoken.payload.request.RegisterRequest;
import com.secure.jwttoken.service.LoginService;
import com.secure.jwttoken.service.LogoutService;
import com.secure.jwttoken.service.RefreshTokenService;
import com.secure.jwttoken.service.RegisterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final RefreshTokenService refreshTokenService;

    private final LogoutService logoutService;
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

    @GetMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(logoutService.logout(request.getHeader("Authorization").substring(7)));
    }
}

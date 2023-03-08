package com.secure.jwttoken.controller;

import com.secure.jwttoken.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LogoutController {
    private final LogoutService logoutService;
    @GetMapping(value = "/log-out")
    @PreAuthorize("hasAnyRole('ADMIN','DB-ADMIN')")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(logoutService.logout(request.getHeader("Authorization").substring(7),true));
    }
}

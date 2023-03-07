package com.secure.jwttoken.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return "Hello Admin";
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('USER')")
    public String userAccess(){
        return "Hello User";
    }

}

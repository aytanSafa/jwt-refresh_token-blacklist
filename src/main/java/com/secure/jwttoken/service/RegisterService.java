package com.secure.jwttoken.service;


import com.secure.jwttoken.repository.RoleRepository;
import com.secure.jwttoken.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


}

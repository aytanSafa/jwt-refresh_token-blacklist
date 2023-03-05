package com.secure.jwttoken.service;


import com.secure.jwttoken.constant.ERole;
import com.secure.jwttoken.entity.Role;
import com.secure.jwttoken.entity.User;
import com.secure.jwttoken.exceptions.UserServiceException;
import com.secure.jwttoken.payload.request.RegisterRequest;
import com.secure.jwttoken.payload.response.RegisterResponse;
import com.secure.jwttoken.repository.RoleRepository;
import com.secure.jwttoken.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterResponse saveNewUser(RegisterRequest request){

    if(userRepository.existsByUsername(request.getUsername())){
        throw new UserServiceException("Username already Exist", HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(request.getEmail())){
        throw new UserServiceException("Email already Exist",HttpStatus.BAD_REQUEST);
    }

    User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .email(request.getEmail())
            .build();

    Set<Role> roleSet = new HashSet<>();

    if (request.getRoles() == null){
     roleSet.add(roleRepository.findByName(ERole.USER).get());
     user.setRoles(roleSet);
    }else{
        Set<ERole> eRoles = request.getRoles();
        for (ERole eRole : eRoles){
            Optional<Role> role = roleRepository.findByName(eRole);
            if(role.isPresent()){
                roleSet.add(role.get());
            }
        }
    }
    user.setRoles(roleSet);
    userRepository.save(user);
    return new RegisterResponse("User registered success");
    }

}

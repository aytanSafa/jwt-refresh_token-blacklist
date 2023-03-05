package com.secure.jwttoken.payload.request;

import com.secure.jwttoken.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private Set<ERole> roles;


}

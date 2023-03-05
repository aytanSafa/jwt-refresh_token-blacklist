package com.secure.jwttoken.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse {

    private String token;
    private String type;
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

}

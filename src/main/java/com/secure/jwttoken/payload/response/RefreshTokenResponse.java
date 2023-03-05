package com.secure.jwttoken.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RefreshTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;

}

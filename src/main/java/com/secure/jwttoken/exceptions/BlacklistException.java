package com.secure.jwttoken.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class BlacklistException extends RuntimeException {
    public static final long  serialVersionUID = 3L;

    public BlacklistException(String message){
        super(message);
    }


}

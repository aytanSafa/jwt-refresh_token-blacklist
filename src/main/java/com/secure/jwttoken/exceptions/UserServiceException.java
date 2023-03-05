package com.secure.jwttoken.exceptions;

import org.springframework.http.HttpStatus;

public class UserServiceException extends RuntimeException{

    private final static long serialVersionUID = 2L;

    private String message;
    private HttpStatus httpStatus;

    public UserServiceException(String message,HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

# Spring Security, Jwt, Refresh Token And Blacklist with Redis

This project generates Json Web Token and Refresh Token with Spring Security. It also creates a blacklist with redis.
The purpose of the project is to use jwt and to explain the philosophy of refresh token and to show how to create a blacklist with redis.

Application uses as database PostgreSql and as cache Redis.

## Description

With Spring Security, first of all, access token creation and verification processes were performed. UUID is used for Refresh Token. Role based Authorization implemented and token blacklisted when user logout.

## Usage

The user will register to the system with username, password, mail and roles. It will then login. After login, the system will return Access Token and Refresh Token response and cache the token. The user will be able to access the necessary services according to their roles. When the token expires, the user will be able to get access tokens again with the refresh token.
Finally, when the user logs out, the token will be added to the blacklist and the system cannot be accessed with the same access token.


## Installation

Application can start with Maven. Dockerfile didn't created but you can use docker-compose.yaml file if you want to install postgreSql and Redis.

```bash
docker-compose up -d
```

You can reach how to request the services with Postman collections in project.

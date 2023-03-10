package com.secure.jwttoken.security.filter;

import com.secure.jwttoken.cache.BlackListService;
import com.secure.jwttoken.cache.CacheService;
import com.secure.jwttoken.exceptions.BlacklistException;
import com.secure.jwttoken.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final BlackListService blackListService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader("Authorization");
        final String jwt = parseJwt(authorization);
try{
    if (jwt != null && blackListService.validateToken(jwt)){

        if(jwtUtils.validateJwtToken(jwt)){

            String username = jwtUtils.getUsernameFromJwtToken(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
    filterChain.doFilter(request,response);
}catch (BlacklistException e){
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.getWriter().write(e.getLocalizedMessage());
}

    }
    private  String parseJwt(String authorization) {
        if (StringUtils.hasText(authorization)){
            return  authorization.substring(7,authorization.length());
        }
        return null;
    }
}

package com.enigma.bankenigma.filter;

import com.enigma.bankenigma.string_properties.ModeString;
import com.enigma.bankenigma.string_properties.ResponseString;
import com.enigma.bankenigma.string_properties.StatusString;
import com.enigma.bankenigma.string_properties.TokenString;
import com.enigma.bankenigma.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class StreamAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(ModeString.AUTHORIZATION);

        if(authorizationHeader==null||!authorizationHeader.startsWith(TokenString.BEARER)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.replace(TokenString.BEARER, "");
        UsernamePasswordAuthenticationToken userPassAuthToken = getUserPassToken(token);
        SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUserPassToken(String token) {
        validateExpirationTimeOfToken(token);
        UserDetails userDetails = jwtTokenUtils.parseToken(token);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    private void validateExpirationTimeOfToken(String token) {
        if(jwtTokenUtils.getExpirationDate(token).getTime()<System.currentTimeMillis()){
            throw new ResponseStatusException(
                    HttpStatus.GATEWAY_TIMEOUT,
                    ResponseString.TOKEN_EXPIRED);
        }
    }
}

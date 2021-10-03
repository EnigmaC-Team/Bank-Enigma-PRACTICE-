package com.enigma.bankenigma.utils;

import com.enigma.bankenigma.service.bank_user_detail_services.BankUserDetailService;
import com.enigma.bankenigma.string_properties.ModeString;
import com.enigma.bankenigma.string_properties.SecretString;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    @Value(SecretString.SECRET)
    private String secret;

    @Autowired
    BankUserDetailService bankUserDetailService;

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();
        claims.put(ModeString.ROLE, userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+(60*1000*12)))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public UserDetails parseToken(String token){
        Jws<Claims> jwsClaims = getClaimsJws(token);

        String username = jwsClaims.getBody().getSubject();
        return bankUserDetailService.loadUserByUsername(username);
    }

    private Jws<Claims> getClaimsJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token);
    }

    public String getNameInToken(String token){
        Jws<Claims> jwsClaims = getClaimsJws(token);
        return jwsClaims.getBody().getSubject();
    }

    public Date getExpirationDate(String token){
        Jws<Claims> jwsClaims = getClaimsJws(token);
        return jwsClaims.getBody().getExpiration();
    }
}

package com.enigma.bankenigma.controller;

import com.enigma.bankenigma.custom.BankUserDetails;
import com.enigma.bankenigma.custom.UserCredential;
import com.enigma.bankenigma.entity.BankUser;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.service.bank_user_detail_services.BankUserDetailService;
import com.enigma.bankenigma.service.bank_user_services.BankUserService;
import com.enigma.bankenigma.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankUserController {

    @Autowired
    BankUserService bankUserService;

    @Autowired
    BankUserDetailService bankUserDetailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @GetMapping("/authenticate/{token}")
    public UserAccount authUser(@PathVariable(name = "token") String token){
        return bankUserService.getUserAccount(token);
    }

    @PostMapping("/userRegistration")
    public BankUser register(@RequestBody BankUser bankUser){
        return bankUserService.create(bankUser);
    }
}

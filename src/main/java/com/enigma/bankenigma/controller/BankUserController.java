package com.enigma.bankenigma.controller;

import com.enigma.bankenigma.entity.BankUser;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.service.user.detail.BankUserDetailService;
import com.enigma.bankenigma.service.user.BankUserService;
import com.enigma.bankenigma.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

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

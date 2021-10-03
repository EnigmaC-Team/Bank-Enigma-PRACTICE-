package com.enigma.bankenigma.controller;

import com.enigma.bankenigma.custom.UserCredential;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.service.account_services.UserAccountService;
import com.enigma.bankenigma.service.bank_user_detail_services.BankUserDetailService;
import com.enigma.bankenigma.string_properties.StatusString;
import com.enigma.bankenigma.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @PostMapping("/signIn")
    public Map<String, Object> getToken(@RequestBody UserCredential userCredential){
        return userAccountService.getToken(userCredential);
    }

    @GetMapping("/account")
    public UserAccount getAccount(@RequestParam String id){
        return userAccountService.checkAccount(id);
    }
}

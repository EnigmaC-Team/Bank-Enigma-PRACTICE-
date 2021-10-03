package com.enigma.bankenigma.controller;

import com.enigma.bankenigma.entity.EWallet;
import com.enigma.bankenigma.service.ewallet.EWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EWalletController {

    @Autowired
    EWalletService eWalletService;

    @PostMapping("/EWallet")
    public EWallet createEWallet(@RequestBody EWallet eWallet){
        return eWalletService.create(eWallet);
    }

    @GetMapping("EWallet")
    public EWallet getEWalletById(@RequestParam String id){
        return eWalletService.checkAccount(id);
    }
}

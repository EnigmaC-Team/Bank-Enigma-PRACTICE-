package com.enigma.bankenigma.controller;

import com.enigma.bankenigma.custom.UserCredential;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.service.account.UserAccountService;
import com.enigma.bankenigma.service.file.FileService;
import com.enigma.bankenigma.string.properties.ModeString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    FileService fileService;

    @PostMapping("/signIn")
    public Map<String, Object> getToken(@RequestBody UserCredential userCredential){
        return userAccountService.getToken(userCredential);
    }

    @GetMapping("/account")
    public UserAccount getAccount(@RequestParam String id){
        return userAccountService.checkAccount(id);
    }

    @PostMapping("/userAccount")
    public void postImageProfile(
            @RequestPart MultipartFile multipartFile,
            @RequestPart String id
    ) throws IOException {
        fileService.saveFileTo(multipartFile, id, ModeString.USER_ACCOUNT);
    }
}

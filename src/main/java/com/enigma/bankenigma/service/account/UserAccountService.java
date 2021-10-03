package com.enigma.bankenigma.service.account;

import com.enigma.bankenigma.custom.UserCredential;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.service.CRUDTemplate;

import java.util.Map;

public interface UserAccountService extends CRUDTemplate<UserAccount> {
    Map<String, Object> getToken(UserCredential userCredential);
    UserAccount getAccountByUsername(String username);
    void saveProfileUserAccount(String id, String profilePath);
}

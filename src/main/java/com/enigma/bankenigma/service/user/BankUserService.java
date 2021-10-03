package com.enigma.bankenigma.service.user;

import com.enigma.bankenigma.entity.BankUser;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.service.CRUDTemplate;

public interface BankUserService extends CRUDTemplate<BankUser> {
    UserAccount getUserAccount(String token);
}

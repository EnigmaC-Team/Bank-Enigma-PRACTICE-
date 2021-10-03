package com.enigma.bankenigma.service.account_services;

import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.repository.UserAccountRepository;
import com.enigma.bankenigma.string_properties.ResponseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserAccountServiceDBImpl implements UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserAccount create(UserAccount userAccount) {
        validateUsernameIfExist(userAccount);
        return userAccountRepository.save(userAccount);
    }

    private void validateUsernameIfExist(UserAccount userAccount) {
        if(userAccountRepository.findByUsername(userAccount.getUsername()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    ResponseString.USERNAME_EXIST);
        }
    }

    @Override
    public UserAccount checkAccount(String id) {
        return null;
    }

    @Override
    public String deleteAccount(String id) {
        return null;
    }
}

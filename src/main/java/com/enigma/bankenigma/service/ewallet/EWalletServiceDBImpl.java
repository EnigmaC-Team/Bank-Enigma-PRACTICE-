package com.enigma.bankenigma.service.ewallet;

import com.enigma.bankenigma.entity.EWallet;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.repository.EWalletRepository;
import com.enigma.bankenigma.service.account.UserAccountService;
import com.enigma.bankenigma.string.properties.LinkUrl;
import com.enigma.bankenigma.string.properties.ResponseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Paths;
import java.util.List;

@Service
public class EWalletServiceDBImpl implements EWalletService{

    @Autowired
    EWalletRepository eWalletRepository;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public EWallet create(EWallet eWallet) {
        UserAccount userAccount = userAccountService.checkAccount(eWallet.getUserAccountId());
        Integer total = userAccount.getSaving()-eWallet.getSaving();
        validateSavings(total);
        userAccount.setSaving(total);
        eWallet.setPassword(passwordEncoder.encode(eWallet.getPassword()));
        eWallet.setUserAccount(userAccount);
        eWallet.setProfilePicture(
                Paths.get("").toAbsolutePath() +
                        LinkUrl.DEFAULT_PROFILE_PATH
        );
        return eWalletRepository.save(eWallet);
    }

    private void validateSavings(Integer total) {
        if(total<25000){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ResponseString.SAVING_IS_NOT_SUFFICIENT
            );
        }
    }

    @Override
    public EWallet checkAccount(String id) {
        EWallet eWallet = eWalletRepository.findById(id).get();
        eWallet.setUserAccountId(eWallet.getUserAccount().getId());
        return eWallet;
    }

    @Override
    public String deleteAccount(String id) {
        return null;
    }

    @Override
    public List<EWallet> getAllEWalletByAccountId(String id) {
        UserAccount userAccount = userAccountService.checkAccount(id);
        return eWalletRepository.findAllByUserAccount(userAccount);
    }
}

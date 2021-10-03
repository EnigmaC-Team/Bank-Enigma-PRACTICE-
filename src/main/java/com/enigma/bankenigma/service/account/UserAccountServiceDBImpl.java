package com.enigma.bankenigma.service.account;

import com.enigma.bankenigma.custom.UserCredential;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.repository.UserAccountRepository;
import com.enigma.bankenigma.service.user.detail.BankUserDetailService;
import com.enigma.bankenigma.string.properties.TokenString;
import com.enigma.bankenigma.string.properties.ResponseString;
import com.enigma.bankenigma.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserAccountServiceDBImpl implements UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    BankUserDetailService bankUserDetailService;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserAccount create(UserAccount userAccount) {
        validateUsernameIfExist(userAccount);
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount checkAccount(String id) {
        return userAccountRepository.findById(id).get();
    }

    private void validateUsernameIfExist(UserAccount userAccount) {
        if(userAccountRepository.findByUsername(userAccount.getUsername()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    ResponseString.USERNAME_EXIST);
        }
    }

    @Override
    public UserAccount getAccountByUsername(String username) {
        return userAccountRepository.findByUsername(username).get();
    }

    @Override
    public void saveProfileUserAccount(String id, String profilePath) {
        UserAccount userAccount = checkAccount(id);
        userAccount.setProfilePicture(profilePath);
        userAccountRepository.save(userAccount);
    }

    public Map<String, Object> getToken(UserCredential userCredential){
        UsernamePasswordAuthenticationToken userPassAuthToken =
                new UsernamePasswordAuthenticationToken(
                        userCredential.getUsername(),
                        userCredential.getPassword()
                );
        authenticationManager.authenticate(userPassAuthToken);
        UserDetails userDetails = bankUserDetailService.loadUserByUsername(
                userCredential.getUsername()
        );

        return tokenWrapper(userDetails);
    }

    private Map<String, Object> tokenWrapper(UserDetails userDetails) {
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, Object> tokenWrapper = new HashMap<>();
        tokenWrapper.put(TokenString.TOKEN, token);
        return tokenWrapper;
    }

    @Override
    public String deleteAccount(String id) {
        return null;
    }
}

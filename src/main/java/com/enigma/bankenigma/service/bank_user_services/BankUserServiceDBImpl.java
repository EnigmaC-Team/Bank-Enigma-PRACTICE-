package com.enigma.bankenigma.service.bank_user_services;

import com.enigma.bankenigma.entity.BankUser;
import com.enigma.bankenigma.entity.UserAccount;
import com.enigma.bankenigma.repository.BankUserRepository;
import com.enigma.bankenigma.service.account_services.UserAccountService;
import com.enigma.bankenigma.service.bank_user_detail_services.BankUserDetailService;
import com.enigma.bankenigma.service.mail_services.BankEmailService;
import com.enigma.bankenigma.string_properties.MailServiceString;
import com.enigma.bankenigma.string_properties.ResponseString;
import com.enigma.bankenigma.string_properties.StatusString;
import com.enigma.bankenigma.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BankUserServiceDBImpl implements BankUserService{

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankUserDetailService bankUserDetailService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    BankEmailService bankEmailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Override
    public BankUser create(BankUser bankUser) {
        String password = bankUser.getPassword();
        bankUser.setPassword(passwordEncoder.encode(bankUser.getPassword()));
        bankUserRepository.save(bankUser);
        String token = getToken(
                bankUser.getUsername(),
                password
        );
        sendMail(
                bankUser.getEmail(),
                String.format(
                        MailServiceString.OTP_MESSAGE_BODY,
                        token
                )
        );
        return bankUser;
    }

    @Override
    public BankUser checkAccount(String id) {
        return bankUserRepository.findById(id).get();
    }

    private void sendMail(String email, String token) {
        bankEmailService.sendSimpleMessage(
                email,
                String.format(
                        MailServiceString.OTP_LINK_BODY,
                        token
                ));
    }

    private String getToken(String username, String password) {
        UsernamePasswordAuthenticationToken userPassAuthToken =
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                );
        authenticationManager.authenticate(userPassAuthToken);
        UserDetails userDetails = bankUserDetailService.loadUserByUsername(username);
        return jwtTokenUtils.generateToken(userDetails);
    }

    @Override
    public UserAccount getUserAccount(String token) {
        validateTokenExpiration(token);
        UserDetails userDetails = jwtTokenUtils.parseToken(token);
        BankUser bankUser = bankUserDetailService.getBankUserByUsername(userDetails.getUsername());
        userAccountService.create(new UserAccount(bankUser));
        bankUser.setVerifiedStatus(StatusString.VERIFIED);
        bankUserRepository.save(bankUser);
        return userAccountService.getAccountByUsername(bankUser.getUsername());
    }

    private void validateTokenExpiration(String token) {
        if(jwtTokenUtils.getExpirationDate(token).getTime()<System.currentTimeMillis()){
            throw new ResponseStatusException(
                    HttpStatus.GATEWAY_TIMEOUT,
                    ResponseString.TOKEN_EXPIRED);
        }
    }

    @Override
    public String deleteAccount(String id) {
        bankUserRepository.delete(checkAccount(id));
        return null;
    }
}

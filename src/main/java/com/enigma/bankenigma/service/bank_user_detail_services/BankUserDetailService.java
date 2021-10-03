package com.enigma.bankenigma.service.bank_user_detail_services;

import com.enigma.bankenigma.custom.BankUserDetails;
import com.enigma.bankenigma.entity.BankUser;
import com.enigma.bankenigma.repository.BankUserDetailRepository;
import com.enigma.bankenigma.string_properties.ResponseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankUserDetailService implements UserDetailsService {

    @Autowired
    BankUserDetailRepository bankUserDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankUser bankUser = getBankUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("User"));

        return new BankUserDetails(
                bankUser.getUsername(),
                bankUser.getPassword(),
                authorities
        );
    }

    private void validateUsername(String username) {
        if(!bankUserDetailRepository.findByUsername(username).isPresent()){
            throw new UsernameNotFoundException(ResponseString.USERNAME_DOESNT_EXIST);
        }
    }

    public BankUser getBankUserByUsername(String username){
        validateUsername(username);
        return bankUserDetailRepository.findByUsername(username).get();
    }
}

package com.enigma.bankenigma.repository;

import com.enigma.bankenigma.entity.BankUser;

import java.util.Optional;

public interface BankUserDetailRepository extends BankUserRepository{
    Optional<BankUser> findByUsername(String username);
}

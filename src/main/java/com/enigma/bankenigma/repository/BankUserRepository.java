package com.enigma.bankenigma.repository;

import com.enigma.bankenigma.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BankUserRepository extends JpaRepository<BankUser, String>, JpaSpecificationExecutor<BankUser> {
}

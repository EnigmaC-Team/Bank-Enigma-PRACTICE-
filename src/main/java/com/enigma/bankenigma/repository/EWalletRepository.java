package com.enigma.bankenigma.repository;

import com.enigma.bankenigma.entity.EWallet;
import com.enigma.bankenigma.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EWalletRepository extends JpaRepository<EWallet, String>, JpaSpecificationExecutor<EWallet> {
    List<EWallet> findAllByUserAccount(UserAccount userAccount);
}

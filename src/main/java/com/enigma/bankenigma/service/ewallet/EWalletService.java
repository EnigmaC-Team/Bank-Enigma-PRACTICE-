package com.enigma.bankenigma.service.ewallet;

import com.enigma.bankenigma.entity.EWallet;
import com.enigma.bankenigma.service.CRUDTemplate;

import java.util.List;

public interface EWalletService extends CRUDTemplate<EWallet> {
    List<EWallet> getAllEWalletByAccountId(String id);
}

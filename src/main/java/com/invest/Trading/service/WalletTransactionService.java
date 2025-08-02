package com.invest.Trading.service;



import com.invest.Trading.Domain.WalletTransactionType;
import com.invest.Trading.model.Wallet;
import com.invest.Trading.model.WalletTransaction;

import java.util.List;

public interface WalletTransactionService {
    WalletTransaction createTransaction(Wallet wallet,
                                        WalletTransactionType type,
                                        String transferId,
                                        String purpose,
                                        Long amount
    );

    List<WalletTransaction> getTransactions(Wallet wallet);

}

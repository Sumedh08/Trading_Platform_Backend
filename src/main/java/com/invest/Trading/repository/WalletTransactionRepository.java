package com.invest.Trading.repository;

import com.invest.Trading.Domain.WalletTransactionType;
import com.invest.Trading.model.Wallet;
import com.invest.Trading.model.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {

    List<WalletTransaction> findByWalletOrderByDateDesc(Wallet wallet);

}

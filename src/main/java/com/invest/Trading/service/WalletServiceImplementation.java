package com.invest.Trading.service;


import com.invest.Trading.Domain.OrderType;
import com.invest.Trading.Domain.WalletTransactionType;
import com.invest.Trading.exception.WalletException;
import com.invest.Trading.model.Order;
import com.invest.Trading.model.User;
import com.invest.Trading.model.Wallet;
import com.invest.Trading.model.WalletTransaction;
import com.invest.Trading.repository.WalletRepository;
import com.invest.Trading.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service

public class WalletServiceImplementation implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    @Autowired
    private WalletTransactionService walletTransactionService;



    public Wallet genrateWallete(User user) {
        Wallet wallet=new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getUserWallet(User user) throws WalletException {

        Wallet wallet = walletRepository.findByUserId(user.getId());
        if (wallet != null) {
            return wallet;
        }

        wallet = genrateWallete(user);
        return wallet;
    }


    @Override
    public Wallet findWalletById(Long id) throws WalletException {
        Optional<Wallet> wallet=walletRepository.findById(id);
        if(wallet.isPresent()){
            return wallet.get();
        }
        throw new WalletException("Wallet not found with id "+id);
    }



    @Override
    public Wallet payOrderPayment(Order order, User user) throws WalletException {
        Wallet wallet = getUserWallet(user);

        WalletTransaction walletTransaction=new WalletTransaction();
        walletTransaction.setWallet(wallet);
        walletTransaction.setPurpose(order.getOrderType()+ " " + order.getOrderItem().getCoin().getId() );

        walletTransaction.setDate(LocalDate.now());
        walletTransaction.setTransferId(order.getOrderItem().getCoin().getSymbol());


        if(order.getOrderType().equals(OrderType.BUY)){
//            walletTransaction.setType(WalletTransactionType.BUY_ASSET);
            walletTransaction.setAmount(-order.getPrice().longValue());
            BigDecimal newBalance = wallet.getBalance().subtract(order.getPrice());

            if (newBalance.compareTo(order.getPrice())<0) {
                System.out.println("inside");
                throw new WalletException("Insufficient funds for this transaction.");
            }
            System.out.println("outside---------- ");
            wallet.setBalance(newBalance);
        }
        else if(order.getOrderType().equals(OrderType.SELL)){
//            walletTransaction.setType(WalletTransactionType.SELL_ASSET);
            walletTransaction.setAmount(order.getPrice().longValue());
            BigDecimal newBalance = wallet.getBalance().add(order.getPrice());
            wallet.setBalance(newBalance);
        }


//        System.out.println("wallet balance "+wallet+"-------"+order.getPrice());
        walletTransactionRepository.save(walletTransaction);
        walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public Wallet deposit(User user, Long amount) throws WalletException {
        if (amount <= 0) {
            throw new WalletException("Deposit amount must be positive");
        }

        Wallet wallet = getUserWallet(user);
        BigDecimal newBalance = wallet.getBalance().add(BigDecimal.valueOf(amount));
        wallet.setBalance(newBalance);

        // Create transaction
        walletTransactionService.createTransaction(
                wallet,
                WalletTransactionType.ADD_MONEY,
                "DEPOSIT",
                "Wallet deposit",
                amount
        );

        return walletRepository.save(wallet);
    }



}

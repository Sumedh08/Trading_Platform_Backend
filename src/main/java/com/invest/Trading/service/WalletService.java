package com.invest.Trading.service;

import com.invest.Trading.exception.WalletException;
import com.invest.Trading.model.Order;
import com.invest.Trading.model.User;
import com.invest.Trading.model.Wallet;

import java.math.BigDecimal;

public interface WalletService {


    Wallet getUserWallet(User user) throws WalletException;

    Wallet deposit(User user, Long amount) throws WalletException;

    public Wallet findWalletById(Long id) throws WalletException;



    public Wallet payOrderPayment(Order order, User user) throws WalletException;



}

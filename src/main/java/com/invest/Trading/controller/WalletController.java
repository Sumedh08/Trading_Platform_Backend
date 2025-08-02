package com.invest.Trading.controller;

import com.invest.Trading.Domain.WalletTransactionType;
import com.invest.Trading.model.*;
import com.invest.Trading.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;


    @Autowired
    private OrderService orderService;

    @Autowired
    private WalletTransactionService walletTransactionService;




    @GetMapping("/api/wallet")
    public ResponseEntity<?> getUserWallet(@RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);

        Wallet wallet = walletService.getUserWallet(user);

        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @GetMapping("/api/wallet/transactions")
    public ResponseEntity<List<WalletTransaction>> getWalletTransaction(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);
        List<WalletTransaction> transactions = walletTransactionService.getTransactions(wallet);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }



    @PutMapping("/api/wallet/deposit/{amount}")
    public ResponseEntity<Wallet> addMoneyToWallet(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long amount) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.deposit(user, amount);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }






    @PutMapping("/api/wallet/order/{orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(@PathVariable Long orderId,
                                                  @RequestHeader("Authorization")String jwt) throws Exception {
        User user =userService.findUserProfileByJwt(jwt);
        System.out.println("-------- "+orderId);
        Order order=orderService.getOrderById(orderId);

        Wallet wallet = walletService.payOrderPayment(order,user);

        return new ResponseEntity<>(wallet,HttpStatus.OK);

    }



}


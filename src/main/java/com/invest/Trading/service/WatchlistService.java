package com.invest.Trading.service;


import com.invest.Trading.model.Coin;
import com.invest.Trading.model.User;
import com.invest.Trading.model.Watchlist;

public interface WatchlistService {

    Watchlist findUserWatchlist(Long userId) throws Exception;

    Watchlist createWatchList(User user);

    Watchlist findById(Long id) throws Exception;

    Coin addItemToWatchlist(Coin coin, User user) throws Exception;
}

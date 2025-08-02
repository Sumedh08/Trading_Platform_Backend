package com.invest.Trading.repository;

import com.invest.Trading.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin,String> {

}

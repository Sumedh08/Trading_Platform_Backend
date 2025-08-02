package com.invest.Trading.repository;

import com.invest.Trading.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

public interface AssetsRepository extends JpaRepository<Asset, Long> {

   List<Asset> findByUserId(Long userId);

   Asset findByUserIdAndCoinId(Long userId, String coinId);

   Asset findByIdAndUserId(Long assetId, Long userId);

   Asset findAssetByUserIdAndCoinId(Long userId,String coinId) throws Exception;


}
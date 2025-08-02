package com.invest.Trading.service;



import com.invest.Trading.exception.AssetNotFoundException;
import com.invest.Trading.model.Asset;
import com.invest.Trading.model.Coin;
import com.invest.Trading.model.User;
import com.invest.Trading.repository.AssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImplementation implements AssetService {
    private final AssetsRepository assetRepository;

    @Autowired
    public AssetServiceImplementation(AssetsRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(User user, Coin coin, double quantity) {
        Asset asset = new Asset();
        asset.setQuantity(quantity);
        asset.setBuyPrice(coin.getCurrentPrice());
        asset.setCoin(coin);
        asset.setUser(user);
        return assetRepository.save(asset);
    }

    public Asset getAssetById(Long assetId) {
        return assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));
    }

    @Override
    public Asset getAssetByUserAndId(Long userId, Long assetId) {
        return assetRepository.findByIdAndUserId(assetId, userId);
    }

    @Override
    public List<Asset> getUsersAssets(Long userId) {
        return assetRepository.findByUserId(userId);
    }

    @Override
    public Asset updateAsset(Long assetId, double quantity) throws AssetNotFoundException {
        Asset oldAsset = getAssetById(assetId);
        if (oldAsset == null) {
            throw new AssetNotFoundException("Asset not found");
        }
        oldAsset.setQuantity(quantity + oldAsset.getQuantity());
        return assetRepository.save(oldAsset);
    }

    @Override
    public Asset findAssetByUserIdAndCoinId(Long userId, String coinId) throws AssetNotFoundException {
        Asset asset = assetRepository.findByUserIdAndCoinId(userId, coinId);
        if (asset == null) {
            throw new AssetNotFoundException("Asset not found for user and coin");
        }
        return asset;
    }

    public void deleteAsset(Long assetId) {
        assetRepository.deleteById(assetId);
    }
}
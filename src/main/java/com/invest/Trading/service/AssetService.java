package com.invest.Trading.service;





import com.invest.Trading.model.Asset;
import com.invest.Trading.model.Coin;
import com.invest.Trading.model.User;

import java.util.List;

public interface AssetService {
    Asset createAsset(User user, Coin coin, double quantity);

    Asset getAssetById(Long assetId);

    Asset getAssetByUserAndId(Long userId,Long assetId);

    List<Asset> getUsersAssets(Long userId);

    Asset updateAsset(Long assetId, double quantity) throws Exception;

    Asset findAssetByUserIdAndCoinId(Long userId,String coinId) throws Exception;

    void deleteAsset(Long assetId);


}

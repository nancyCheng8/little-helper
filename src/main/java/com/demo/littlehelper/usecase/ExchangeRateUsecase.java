 package com.demo.littlehelper.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.littlehelper.gateway.database.userexchangerates.UserExchangeRatesResource;
import com.demo.littlehelper.gateway.network.exchangerate.WsExchangeGateway;
import com.demo.littlehelper.model.dto.exchangerate.WsExchangeRate;
import com.demo.littlehelper.model.dto.userexchangerates.UserExchangeRates;

@Service
 public class ExchangeRateUsecase {
    
    @Autowired
    private WsExchangeGateway wsExchangeGateway;
    
    @Autowired
    private UserExchangeRatesResource userExchangeRatesResource;
    
    /**
     * 查詢台幣兌外幣匯率
     * @return WsExchangeRate
     */
    public WsExchangeRate queryTWDExchangeRate() {
        return wsExchangeGateway.queryTWDExchangeRate();
    }
    
    /**
     * 儲存查詢台幣兌外幣匯率
     * @param rateToSave
     */
    public void saveUserExchangeRate(UserExchangeRates rateToSave) {
        userExchangeRatesResource.saveUserExchangeRate(rateToSave);
    }
    
    /**
     * 查詢使用者儲存的匯率資料
     * @param userId
     * @return List<UserExchangeRates>
     */
    public List<UserExchangeRates> queryUserExchangeRateRecord(Long userId) {
        return userExchangeRatesResource.queryUserExchangeRateRecord(userId);
    }
    
    /**
     * 刪除紀錄使用者儲存的匯率資料
     * @param exchangeRateRecordIds
     */
    public void deleteUserExchangeRateRecord(List<Long> exchangeRateRecordIds) {
        userExchangeRatesResource.deleteUserExchangeRateRecord(exchangeRateRecordIds);
    }
}

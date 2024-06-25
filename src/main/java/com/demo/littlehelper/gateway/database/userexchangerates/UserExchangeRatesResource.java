 package com.demo.littlehelper.gateway.database.userexchangerates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.littlehelper.model.dto.userexchangerates.UserExchangeRates;

import jakarta.transaction.Transactional;

@Component
 public class UserExchangeRatesResource {

    @Autowired
    private UserExchangeRatesRepository userExchangeRatesRepository;
    
    /**
     * 儲存使用者選擇的匯率
     * @param rateToSave
     */
    @Transactional
    public void saveUserExchangeRate(UserExchangeRates rateToSave) {
        UserExchangeRatesEntity entity = this.userExchangeRatesToEntity(rateToSave);
        userExchangeRatesRepository.save(entity);
    }

    /**
     * 查詢使用者儲存的匯率資料
     * 
     * @param userId
     * @return List<UserExchangeRates>
     */
    public List<UserExchangeRates> queryUserExchangeRateRecord(Long userId) {
        return userExchangeRatesRepository.queryByUserIdOrderByCurrencyCodeAscQueryDateStringAsc(userId).stream()
                .map(this::userExchangeRatesToEntity)
                .toList();
    }
    
    /**
     * 刪除紀錄使用者儲存的匯率資料
     * @param exchangeRateRecordIds
     */
    @Transactional
    public void deleteUserExchangeRateRecord(List<Long> exchangeRateRecordIds) {
        userExchangeRatesRepository.deleteAllById(exchangeRateRecordIds);
    }
    
    /**
     * UserExchangeRates 轉為 entity
     * @param rate
     * @return UserExchangeRatesEntity
     */
    private UserExchangeRatesEntity userExchangeRatesToEntity(UserExchangeRates rate) {
        return UserExchangeRatesEntity.builder()
                .id(rate.getId())
                .userId(rate.getUserId())
                .queryDateString(rate.getQueryDateString())
                .currencyDescription(rate.getCurrencyDescription())
                .bankBuyRate(rate.getBankBuyRate())
                .bankSellRate(rate.getBankSellRate())
                .currencyCode(rate.getCurrencyCode())
                .build();
    }
    
    /**
     * entity 轉為 UserExchangeRates
     * @param entity
     * @return
     */
    private UserExchangeRates userExchangeRatesToEntity(UserExchangeRatesEntity entity) {
        return UserExchangeRates.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .queryDateString(entity.getQueryDateString())
                .currencyDescription(entity.getCurrencyDescription())
                .bankBuyRate(entity.getBankBuyRate())
                .bankSellRate(entity.getBankSellRate())
                .currencyCode(entity.getCurrencyCode())
                .build();
    }
}

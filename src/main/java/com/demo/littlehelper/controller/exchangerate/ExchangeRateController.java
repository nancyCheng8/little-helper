package com.demo.littlehelper.controller.exchangerate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.littlehelper.controller.exchangerate.model.DeleteUserExchangeRateRecordRq;
import com.demo.littlehelper.controller.exchangerate.model.QueryExchangeRateRs;
import com.demo.littlehelper.controller.exchangerate.model.QueryUserExchangeRateRecordRq;
import com.demo.littlehelper.controller.exchangerate.model.QueryUserExchangeRateRecordRs;
import com.demo.littlehelper.controller.exchangerate.model.SaveExchangeRateRq;
import com.demo.littlehelper.model.dto.exchangerate.WsExchangeRate;
import com.demo.littlehelper.model.dto.userexchangerates.UserExchangeRates;
import com.demo.littlehelper.usecase.ExchangeRateUsecase;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "匯率流程")
@RestController
@RequestMapping(path = "/exchange-rate")
public class ExchangeRateController {
    
    @Autowired
    private ExchangeRateUsecase exchangeRateUsecase;
    
    @ApiOperation("查詢台幣兌外幣匯率")
    @PostMapping(path = "/query")
    public ResponseEntity<QueryExchangeRateRs> queryExchangeRate(){
        WsExchangeRate result = exchangeRateUsecase.queryTWDExchangeRate();
        
        QueryExchangeRateRs rs = this.wsExchangeRateToQueryExchangeRateRs(result);
        return ResponseEntity.ok(rs);
    }   
    
    @ApiOperation("儲存查詢台幣兌外幣匯率")
    @PostMapping(path = "/save")
    public ResponseEntity<QueryExchangeRateRs> saveExchangeRate(@Valid @RequestBody SaveExchangeRateRq rq){
        UserExchangeRates rateToSave = this.saveExchangeRateRqToUserExchangeRates(rq);
        
        exchangeRateUsecase.saveUserExchangeRate(rateToSave);
        return ResponseEntity.ok().build();
    } 
    
    @ApiOperation("查詢使用者儲存的匯率資料")
    @PostMapping(path = "/query-user-exchang-rate-record")
    public ResponseEntity<QueryUserExchangeRateRecordRs> queryUserExchangeRateRecord(@Valid @RequestBody QueryUserExchangeRateRecordRq rq){
        // to UserExchangeRates
        Long userId = rq.getUserId();
        List<UserExchangeRates> result = exchangeRateUsecase.queryUserExchangeRateRecord(userId);
        QueryUserExchangeRateRecordRs rs = new QueryUserExchangeRateRecordRs();
        rs.setUserExchangeRatesList(result);
        return ResponseEntity.ok(rs);
    }
    
    @ApiOperation("刪除紀錄使用者儲存的匯率資料")
    @PostMapping(path = "/delete-user-exchang-rate-record")
    public ResponseEntity<?> deleteUserExchangeRateRecord(@Valid @RequestBody DeleteUserExchangeRateRecordRq rq) {
        List<Long> exchangeRateRecordIds = rq.getIds();
        exchangeRateUsecase.deleteUserExchangeRateRecord(exchangeRateRecordIds);
        return ResponseEntity.ok().build();
    }
    
    /**
     * 將 WsExchangeRate 轉為 QueryExchangeRateRs
     * @param result
     * @return
     */
    private QueryExchangeRateRs wsExchangeRateToQueryExchangeRateRs(WsExchangeRate result) {
        return QueryExchangeRateRs.builder()
                .queryDateString(result.getQueryDateString())
                .twdExchangeRate(result.getTwdExchangeRate())
                .build();
    }
    
    /**
     * 將 SaveExchangeRateRq 轉為 UserExchangeRates
     * @param rq
     * @return
     */
    private UserExchangeRates saveExchangeRateRqToUserExchangeRates(SaveExchangeRateRq rq) {
        return UserExchangeRates.builder()
                .userId(rq.getUserId())
                .queryDateString(rq.getQueryDateString())
                .currencyDescription(rq.getCurrencyDescription())
                .bankBuyRate(rq.getBankBuyRate())
                .bankSellRate(rq.getBankSellRate())
                .currencyCode(rq.getCurrencyCode())
                .build();
    }
    
}

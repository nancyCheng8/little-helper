package com.demo.littlehelper.gateway.database.userexchangerates;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_exchange_rates")
public class UserExchangeRatesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 使用者id */
    @Column(name = "user_Id")
    private Long userId;

    /** 匯率查詢日期(yyyy/MM/dd hh:MM:ss) */
    @Column(name = "query_date_string")
    private String queryDateString;

    /** 幣別中文描述 */
    @Column(name = "currency_description")
    private String currencyDescription;

    /** 銀行買進匯率 */
    @Column(name = "bank_buy_rate")
    private BigDecimal bankBuyRate;

    /** 銀行賣出匯率 */
    @Column(name = "bank_sell_rate")
    private BigDecimal bankSellRate;

    /** 幣別代碼 */
    @Column(name = "currency_code")
    private String currencyCode;
}

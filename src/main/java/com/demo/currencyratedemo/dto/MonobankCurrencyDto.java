package com.demo.currencyratedemo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonobankCurrencyDto {

    private int currencyCodeA;
    private int currencyCodeB;
    private long dateTime;
    private BigDecimal rateSell;
    private BigDecimal rateBuy;
    private BigDecimal rateCross;
}

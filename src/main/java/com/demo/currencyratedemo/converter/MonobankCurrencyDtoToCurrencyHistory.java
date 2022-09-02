package com.demo.currencyratedemo.converter;

import com.demo.currencyratedemo.dto.MonobankCurrencyDto;
import com.demo.currencyratedemo.model.CurrencyHistory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MonobankCurrencyDtoToCurrencyHistory implements Converter<MonobankCurrencyDto, CurrencyHistory> {

    @Override
    public CurrencyHistory convert(MonobankCurrencyDto source) {
        final CurrencyHistory currencyHistory = new CurrencyHistory();
        currencyHistory.setCurrencyCodeA(source.getCurrencyCodeA());
        currencyHistory.setCurrencyCodeB(source.getCurrencyCodeB());
        currencyHistory.setCreatedAt(LocalDateTime.now());
        currencyHistory.setRateBuy(source.getRateBuy());
        currencyHistory.setRateCross(source.getRateCross());
        currencyHistory.setRateSell(source.getRateSell());
        return currencyHistory;
    }
}

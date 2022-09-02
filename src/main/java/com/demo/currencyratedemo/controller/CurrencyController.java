package com.demo.currencyratedemo.controller;

import com.demo.currencyratedemo.model.CurrencyHistory;
import com.demo.currencyratedemo.service.CurrencyService;
import com.demo.currencyratedemo.util.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/usd-to-uah")
    public String getActualRate() {
        CurrencyHistory currency = currencyService.getLatestCurrencyByCurrencyAAndCurrencyB(
                Currency.USD_ISO_4217, Currency.UAH_ISO_4217);

        return String.format("Rate sell is %s , rate buy is %s, rate cross is %s",
                currency.getRateSell(), currency.getRateBuy(), currency.getRateCross());
    }
}

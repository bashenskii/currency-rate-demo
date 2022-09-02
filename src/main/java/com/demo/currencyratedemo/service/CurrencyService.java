package com.demo.currencyratedemo.service;

import com.demo.currencyratedemo.model.CurrencyHistory;
import com.demo.currencyratedemo.repository.CurrencyHistoryRepository;
import com.demo.currencyratedemo.util.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyHistoryRepository currencyHistoryRepository;

    private final CurrencyReceiverService currencyReceiverService;

    public CurrencyHistory getLatestCurrencyByCurrencyAAndCurrencyB(Currency currencyA, Currency currencyB) {
        CurrencyHistory currencyHistory = currencyHistoryRepository
                .findTopByCurrencyCodeAAndCurrencyCodeBOrderByCreatedAtDesc(currencyA.getCurrencyCode(),
                        currencyB.getCurrencyCode());

        if (currencyHistory == null) {
            return currencyReceiverService.getCurrencyByCodeAAndCodeBAndSave(currencyA, currencyB);
        }
        return currencyHistory;
    }
}

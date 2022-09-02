package com.demo.currencyratedemo.service;

import com.demo.currencyratedemo.config.MonobankApiConfig;
import com.demo.currencyratedemo.dto.MonobankCurrencyDto;
import com.demo.currencyratedemo.model.CurrencyHistory;
import com.demo.currencyratedemo.repository.CurrencyHistoryRepository;
import com.demo.currencyratedemo.util.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyReceiverService {

    private final RestTemplate restTemplate;
    private final MonobankApiConfig monobankApiConfig;

    private final ConversionService conversionService;

    private final CurrencyHistoryRepository currencyHistoryRepository;

    public List<MonobankCurrencyDto> listAllRates() {
        try {
            return restTemplate.exchange(
                    monobankApiConfig.getUrl(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<MonobankCurrencyDto>>() {
                    }).getBody();

        } catch (
                HttpStatusCodeException exception) {
            log.error("exception occurred", exception);
            if (HttpStatus.BAD_REQUEST == exception.getStatusCode()) {
                throw new IllegalArgumentException("We cannot receive list of currencies from api " + exception.getMessage());
            }
            throw exception;
        }
    }

    public CurrencyHistory getCurrencyByCodeAAndCodeBAndSave(Currency currencyA, Currency currencyB) {
        MonobankCurrencyDto monobankCurrencyDto = this.listAllRates()
                .stream()
                .filter(x -> x.getCurrencyCodeA() == currencyA.getCurrencyCode() && x.getCurrencyCodeB() == currencyB.getCurrencyCode())
                .max(Comparator.comparingLong(MonobankCurrencyDto::getDateTime))
                .orElse(null);

        if (monobankCurrencyDto != null) {
            final CurrencyHistory converted = conversionService.convert(monobankCurrencyDto, CurrencyHistory.class);
            if (converted != null) {
                currencyHistoryRepository.save(converted);
            }
            return converted;
        }
        throw new IllegalArgumentException("Couldn't get actual rate");
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void saveRateByCron() {
        this.getCurrencyByCodeAAndCodeBAndSave(Currency.USD_ISO_4217, Currency.UAH_ISO_4217);
    }
}

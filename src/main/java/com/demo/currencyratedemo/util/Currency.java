package com.demo.currencyratedemo.util;

import org.apache.commons.lang3.StringUtils;

import static java.lang.String.format;

public enum Currency {

    USD_ISO_4217("USD", 840),
    UAH_ISO_4217("UAH", 980);

    private final String currencyName;
    private final int currencyCode;

    Currency(String currencyName, int currencyCode) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    public static Currency of(String value) {
        for (Currency status : Currency.values()) {
            if (!StringUtils.isEmpty(value) && value.equalsIgnoreCase(status.currencyName)) {
                return status;
            }
        }

        throw new IllegalArgumentException(format("Unknown payment status value: %s", value));
    }

    public int getCurrencyCode() {
        return currencyCode;
    }
}

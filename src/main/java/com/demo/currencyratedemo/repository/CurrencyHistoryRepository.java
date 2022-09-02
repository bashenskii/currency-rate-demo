package com.demo.currencyratedemo.repository;

import com.demo.currencyratedemo.model.CurrencyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyHistoryRepository extends JpaRepository<CurrencyHistory, Long> {

    CurrencyHistory findTopByCurrencyCodeAAndCurrencyCodeBOrderByCreatedAtDesc(int currencyCodeA, int currencyCodeB);
}

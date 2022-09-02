package com.demo.currencyratedemo.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class CurrencyHistory {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private int currencyCodeA;

    private int currencyCodeB;

    private LocalDateTime createdAt;

    private BigDecimal rateSell;

    private BigDecimal rateBuy;

    private BigDecimal rateCross;

}

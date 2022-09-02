package com.demo.currencyratedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyRateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateDemoApplication.class, args);
	}

}

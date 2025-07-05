package com.myproject.banking;

import com.myproject.banking.entity.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
		Account ac = new Account(1L,"abc", 100.0);
		System.out.println(ac.getAccountHolderName());
	}

}

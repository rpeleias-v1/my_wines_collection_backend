package com.rodrigopeleias.minhacolecaovinhos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class MinhaColecaoVinhosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhaColecaoVinhosApplication.class, args);
	}
}

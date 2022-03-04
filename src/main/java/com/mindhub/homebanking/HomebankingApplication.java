package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static java.time.LocalDateTime.now;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository){
		return (args) -> {
			Client cliente1 = new Client("Cristian", "Fuentes","cristianfuentesmozo@gmail.com");
			Client cliente2 = new Client("Melba", "Morel", "melba@mindhub.com");
			clientRepository.save(cliente1);
			clientRepository.save(cliente2);

			Account account1 = new Account("VIN001", now(), 5000.0,cliente1);
			Account account2 = new Account("VIN002", now().plusDays(1), 7500.0, cliente1);
			Account account3 = new Account("VIN003",now().plusWeeks(1), 10000.0, cliente2);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);

		};
	}

}

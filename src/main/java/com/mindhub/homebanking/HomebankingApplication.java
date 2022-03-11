package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository,
									  TransactionRepository transactionRepository, LoanRepository loanRepository,
									  ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return (args) -> {
			Client cliente1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("123456"));
			Client cliente2 = new Client("Cristian", "Fuentes","cristianfuentesmozo@gmail.com", passwordEncoder.encode("123456"));
			Client admin = new Client("admin", "admin", "admin@admin.cl", passwordEncoder.encode("123456"));
			clientRepository.save(cliente1);
			clientRepository.save(cliente2);
			clientRepository.save(admin);

			Account account1 = new Account("VIN001", now(), 5000.0,cliente1);
			Account account2 = new Account("VIN002", now().plusDays(1), 7500.0, cliente1);
			Account account3 = new Account("VIN003",now().plusWeeks(1), 10000.0, cliente2);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);

			Transaction transaction1 = new Transaction(TransactionType.DEBIT, -5000.0, "compra 1", now(), account1);
			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 15000.0, "abono 1", now(), account2);
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);

			Loan loan1 = new Loan(Arrays.asList(12,24,36,48,60),"Hipotecario", 500000.0);
			Loan loan2 = new Loan(Arrays.asList(6,12,24), "Personal", 100000.0);
			Loan loan3 = new Loan(Arrays.asList(6,12,24,36), "Automotriz", 300000.0);
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(400000,60,cliente1,loan1);
			ClientLoan clientLoan2 = new ClientLoan(50000,12,cliente1,loan2);
			ClientLoan clientLoan3 = new ClientLoan(100000,24,cliente2,loan2);
			ClientLoan clientLoan4 = new ClientLoan(200000,36,cliente1,loan3);
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card((cliente1.getFirstName()+" "+cliente1.getLastName()),CardType.DEBIT,CardColor.SILVER,"123-46-579",123,now().plusYears(5),now(),cliente1);
			Card card2 = new Card((cliente1.getFirstName()+" "+cliente1.getLastName()),CardType.DEBIT,CardColor.TITANIUM,"45-621-38-5",456,now().plusYears(5),now(),cliente1);
			Card card3 = new Card((cliente2.getFirstName()+" "+cliente2.getLastName()),CardType.CREDIT,CardColor.GOLD,"68-451-324",789,now().plusYears(5),now(),cliente2);
			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);

		};
	}

}

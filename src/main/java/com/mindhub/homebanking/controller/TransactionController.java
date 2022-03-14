package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
public class TransactionController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    @RequestMapping(path = "/api/transactions", method = RequestMethod.POST)
    public ResponseEntity<Object> transaction(Authentication authentication, @RequestParam Double amount, @RequestParam String description, @RequestParam String fromAccountNumber, @RequestParam String toAccountNumber) {
        Client currentClient = clientRepository.findByEmail(authentication.getName());
        // Verificar que los datos no vengan vacios
        if (amount.equals("") || description.isEmpty() || fromAccountNumber.isEmpty() || toAccountNumber.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        // Varificar que la cuenta de origen y destino no sean iguales
        if (fromAccountNumber.equals(toAccountNumber)) {
            return new ResponseEntity<>("Check Account", HttpStatus.FORBIDDEN);
        }
        //Verificar que la cuenta de destino exista
        if (accountRepository.findAccountByNumber(toAccountNumber).getNumber().isEmpty()){
            return new ResponseEntity<>("Check Account", HttpStatus.FORBIDDEN);
        }
        // Verificar que la cuenta de origen pertenesca al usuario current
        if (currentClient.getAccounts().stream().filter(account -> account.getNumber().equals(fromAccountNumber)).collect(Collectors.toList()).isEmpty()) {
            return new ResponseEntity<>("Check Account", HttpStatus.FORBIDDEN);
        }
        if (accountRepository.findAccountsByNumber(fromAccountNumber).getBalance() < amount) {
            return new ResponseEntity<>("Check Amount", HttpStatus.FORBIDDEN);
        }


        double balanceOrigin = accountRepository.findAccountsByNumber(fromAccountNumber).getBalance();
        accountRepository.findAccountsByNumber(fromAccountNumber).setBalance(balanceOrigin - amount);
        Transaction transactionDebit = new Transaction(TransactionType.DEBIT, -amount, toAccountNumber + " " + description, LocalDateTime.now(), accountRepository.findAccountsByNumber(fromAccountNumber));
        transactionRepository.save(transactionDebit);

        double balanceTo = accountRepository.findAccountsByNumber(toAccountNumber).getBalance();
        accountRepository.findAccountsByNumber(toAccountNumber).setBalance(balanceTo + amount);
        Transaction transactionCredit = new Transaction(TransactionType.CREDIT, amount, toAccountNumber + " " + description, LocalDateTime.now(), accountRepository.findAccountsByNumber(toAccountNumber));
        transactionRepository.save(transactionCredit);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}

package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());

    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id, Authentication authentication) {
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }

    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount (Authentication authentication){
        Client currentClient = clientRepository.findByEmail(authentication.getName());
        Set<Account> accountsCurrentClient = currentClient.getAccounts();
        if (accountsCurrentClient.size() <= 3 ) {
            Random random = new Random();
            String accountNumber = "VIN-"+random.nextInt(99999999 + 1)+1;
            accountRepository.save(new Account(accountNumber, LocalDateTime.now(),0.0 ,currentClient));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Excced Accounts ", HttpStatus.FORBIDDEN);
        }
    };


}

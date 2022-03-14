package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList());

    }

    ;

    @RequestMapping("clients/{id}")
    public ClientDTO getClient(@PathVariable Long id, Authentication authentication) {
        if (authentication.getName() == clientRepository.findById(id).get().getEmail()){
        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);
        } else {
            return null;
        }

    }

    ;
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password
    ) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) != null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }
        Client newClient = clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
        Random random = new Random();
        String accountNumber = "VIN-"+random.nextInt(99999999 + 1)+1;
        accountRepository.save(new Account(accountNumber, LocalDateTime.now(),0.0 , newClient));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @RequestMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication) {
        Client clientCurrent =  clientRepository.findByEmail(authentication.getName());
        return new ClientDTO(clientCurrent);

    }
    @RequestMapping("/clients/current/accounts")
    public List<AccountDTO> getAccounts(Authentication authentication) {
        return accountRepository.searchAccountsByOwner(clientRepository.findByEmail(authentication.getName())).stream().map(AccountDTO::new).collect(toList());
    }

}

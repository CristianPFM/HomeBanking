package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;


@RestController
public class CardController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ClientRepository clientRepository;


    @RequestMapping(path = "/api/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> createCard(@RequestParam CardType cardType, @RequestParam CardColor cardColor, Authentication authentication) {
        Client currentClient = clientRepository.findByEmail(authentication.getName());
        String fullName = currentClient.getFirstName() + " " + currentClient.getLastName();
        String cardNumber = genereteRandom(1000,9999)+"-"+genereteRandom(1000,9999)+"-"+genereteRandom(1000,9999)+"-"+genereteRandom(1000,9999);
        if (cardRepository.countCardByClientAndType(currentClient, cardType) < 3) {
            cardRepository.save(new Card(fullName, cardType, cardColor, cardNumber, Integer.parseInt(genereteRandom(100, 999)), LocalDateTime.now().plusYears(5), LocalDateTime.now(),  currentClient));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Excced Cards ", HttpStatus.FORBIDDEN);
        }
    }

    public String genereteRandom (int min, int max) {
        Random random = new Random();
        return String.valueOf(random.nextInt((max + min) + min));
    }
}

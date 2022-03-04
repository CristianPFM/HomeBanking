package com.mindhub.homebanking.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;

    @RequestMapping("/clients")
    public List<Client> getClients() {
    return clientRepository.findAll();

    };



}
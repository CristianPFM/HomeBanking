package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDateTime;

public class AccountDTO{
    private Long id;
    private String number;
    private LocalDateTime creationDate;
    private Double balance;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

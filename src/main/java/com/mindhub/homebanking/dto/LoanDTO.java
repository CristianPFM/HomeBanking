package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Loan;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

public class LoanDTO {
    private long id;
    @ElementCollection
    private List<Integer> payments = new ArrayList<>();
    private String name;
    private double maxAmount;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.payments = loan.getPayments();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
}

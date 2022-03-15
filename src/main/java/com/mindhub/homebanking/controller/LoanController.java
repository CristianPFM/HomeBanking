package com.mindhub.homebanking.controller;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.LoanApplicationDTO;
import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
public class LoanController {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientLoanRepository clientLoanRepository;

    @RequestMapping("/api/loans")
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(toList());
    }


    @Transactional
    @RequestMapping(path = "/api/loans", method = RequestMethod.POST)
    public ResponseEntity<Object> transaction(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {
        Client clientCurrent = clientRepository.findByEmail(authentication.getName());
        Loan loanCurrent = loanRepository.getById(loanApplicationDTO.getLoanId());
        Account accountCurrent = accountRepository.findAccountsByNumber(loanApplicationDTO.getToAccountNumber());

        //Verificacion que los datos sean validos o no vacios
        if (loanApplicationDTO.getAmount() <= 0 || loanApplicationDTO.getPayments() == 0 || loanApplicationDTO.getToAccountNumber().isEmpty() || loanApplicationDTO.getLoanId()==0) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        //Verificacion que el prestamo exista
        if (loanRepository.getById(loanApplicationDTO.getLoanId()).equals("")){
            return new ResponseEntity<>("Missing data - Check Loan ID", HttpStatus.FORBIDDEN);
        }

        //Verificar que el monto solicitado no exceda el monto maximo del prestamo
        if (loanApplicationDTO.getAmount() > loanCurrent.getMaxAmount()){
            return new ResponseEntity<>("Check Max Amount", HttpStatus.FORBIDDEN);
        }

        //Verificar que la cantidad de cuotas se encuentre entre las disponibles para el credito
        if (!loanCurrent.getPayments().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("Check Payments", HttpStatus.FORBIDDEN);
        }

        // Verificar que la cuenta de destino exista
        if (accountCurrent.getNumber().isEmpty()){
            return new ResponseEntity<>("Check To Account", HttpStatus.FORBIDDEN);
        }

        //Verificar que la cuenta de destino sea del usuario

        if (accountCurrent.getOwner() != clientCurrent){
            return new ResponseEntity<>("Check from account on User ", HttpStatus.FORBIDDEN);
        }

        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount() * 1.2,loanApplicationDTO.getPayments(),clientCurrent,loanCurrent);
        clientLoanRepository.save(clientLoan);

        Transaction transactionCredit = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), "Loan Approved " + loanCurrent.getName(), LocalDateTime.now(), accountCurrent);
        transactionRepository.save(transactionCredit);

        accountCurrent.setBalance(accountCurrent.getBalance() + loanApplicationDTO.getAmount());
        accountRepository.save(accountCurrent);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    ;
}

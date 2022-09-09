package com.anvilguard.the_anvilguard_backend.controller;


import com.anvilguard.the_anvilguard_backend.controller.dto.TransactionCreationRequestDto;
import com.anvilguard.the_anvilguard_backend.model.Transaction;
import com.anvilguard.the_anvilguard_backend.service.BountyService;
import com.anvilguard.the_anvilguard_backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ledger")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/deposits")
    public ResponseEntity<List<Transaction>> getAllDeposits() {
        return new ResponseEntity<>(transactionService.findAllDeposits(), HttpStatus.OK);
    }

    @GetMapping("/withdrawals")
    public ResponseEntity<List<Transaction>> getAllWithdrawals() {
        return new ResponseEntity<>(transactionService.findAllWithdrawals(), HttpStatus.OK);

    }

    @GetMapping("/{playername}")
    public ResponseEntity<List<Transaction>> getAllByPlayer(@PathVariable String playername) {
        return new ResponseEntity<>(transactionService.findAllByPlayer(playername), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> makeDeposit(@RequestBody TransactionCreationRequestDto transactionCreationRequestDto) {
        System.out.println("MAKING DEPOSIT");
        Transaction t = transactionService.createTransaction(transactionCreationRequestDto);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }


}

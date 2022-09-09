package com.anvilguard.the_anvilguard_backend.controller;

import com.anvilguard.the_anvilguard_backend.model.TRANSACTION_TYPE;
import com.anvilguard.the_anvilguard_backend.model.Transaction;
import com.anvilguard.the_anvilguard_backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/guild")
public class LedgerController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Float> getTotalLedgerGold() {
        List<Transaction> tl = transactionService.findAll();
        float total = 0;
        for (Transaction t :
                tl) {
            if (t.getType().equals(TRANSACTION_TYPE.DEPOSIT)) {
                total += t.getAmount();
            }
            if (t.getType().equals(TRANSACTION_TYPE.WITHDRAWAL)) {
                total -= t.getAmount();
            }
        }
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}

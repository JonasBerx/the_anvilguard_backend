package com.anvilguard.the_anvilguard_backend.service;

import com.anvilguard.the_anvilguard_backend.controller.dto.TransactionCreationRequestDto;
import com.anvilguard.the_anvilguard_backend.model.Player;
import com.anvilguard.the_anvilguard_backend.model.TRANSACTION_TYPE;
import com.anvilguard.the_anvilguard_backend.model.Transaction;
import com.anvilguard.the_anvilguard_backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PlayerService playerService;

    public List<Transaction> findAll() {

        return transactionRepository.findAll();
    }

    public List<Transaction> findAllByPlayer(String player) {
        Player p = playerService.findPlayerByPlayerName(player);

        return transactionRepository.findAllByPlayer(p).orElse(new ArrayList<>());
    }

    public List<Transaction> findAllDeposits() {
        return transactionRepository.findByType(TRANSACTION_TYPE.DEPOSIT).orElse(new ArrayList<>());
    }

    public List<Transaction> findAllWithdrawals() {
        return transactionRepository.findByType(TRANSACTION_TYPE.WITHDRAWAL).orElse(new ArrayList<>());
    }

    public Transaction createTransaction(TransactionCreationRequestDto transactionCreationRequestDto) {
        Transaction t;
        if (playerService.findPlayerByPlayerName(transactionCreationRequestDto.getPlayerName()) != null) {
            t = Transaction.builder()
                    .player(playerService.findPlayerByPlayerName(transactionCreationRequestDto.getPlayerName()))
                    .amount(transactionCreationRequestDto.getAmount())
                    .type(TRANSACTION_TYPE.valueOf(transactionCreationRequestDto.getType().toUpperCase()))
                    .build();
        } else {
            t = Transaction.builder()
                    .player(playerService.createPlayer(transactionCreationRequestDto.getPlayerName(), true))
                    .amount(transactionCreationRequestDto.getAmount())
                    .type(TRANSACTION_TYPE.valueOf(transactionCreationRequestDto.getType().toUpperCase()))
                    .build();
        }

        return transactionRepository.save(t);
    }
}

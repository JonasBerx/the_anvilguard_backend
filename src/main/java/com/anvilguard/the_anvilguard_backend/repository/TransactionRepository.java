package com.anvilguard.the_anvilguard_backend.repository;

import com.anvilguard.the_anvilguard_backend.model.Player;
import com.anvilguard.the_anvilguard_backend.model.TRANSACTION_TYPE;
import com.anvilguard.the_anvilguard_backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findAllByPlayer(Player player);

    Optional<List<Transaction>> findByType(TRANSACTION_TYPE type);
}

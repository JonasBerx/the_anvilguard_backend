package com.anvilguard.the_anvilguard_backend.repository;

import com.anvilguard.the_anvilguard_backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {

    Optional<Player> findByPlayerName(String playerName);

    Optional<List<Player>> findAllByFriendly(boolean friendly);
}

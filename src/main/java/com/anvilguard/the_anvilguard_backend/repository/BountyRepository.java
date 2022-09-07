package com.anvilguard.the_anvilguard_backend.repository;

import com.anvilguard.the_anvilguard_backend.model.Bounty;
import com.anvilguard.the_anvilguard_backend.model.PLAYER_CLASS;
import com.anvilguard.the_anvilguard_backend.model.PLAYER_RACE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BountyRepository extends JpaRepository<Bounty, Long> {

    Optional<Bounty> findByPlayerName(String playerName);

    Optional<List<Bounty>> findAllByPlayerRace(PLAYER_RACE playerRace);

    Optional<List<Bounty>> findAllByPlayerClass(PLAYER_CLASS playerClass);


}

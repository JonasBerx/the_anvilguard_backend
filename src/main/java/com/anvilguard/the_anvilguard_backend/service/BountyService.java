package com.anvilguard.the_anvilguard_backend.service;

import com.anvilguard.the_anvilguard_backend.model.Bounty;
import com.anvilguard.the_anvilguard_backend.model.PLAYER_CLASS;
import com.anvilguard.the_anvilguard_backend.model.PLAYER_RACE;
import com.anvilguard.the_anvilguard_backend.repository.BountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BountyService {

    @Autowired
    private BountyRepository bountyRepository;

    public List<Bounty> findAll() {
        return bountyRepository.findAll();
    }

    public List<Bounty> findAllByRace(String p_race) {
        return bountyRepository.findAllByPlayerRace(PLAYER_RACE.valueOf(p_race.toUpperCase())).orElseThrow();
    }

    public List<Bounty> findAllByClass(String p_class) {
        return bountyRepository.findAllByPlayerClass(PLAYER_CLASS.valueOf(p_class.toUpperCase())).orElseThrow();
    }

    public Bounty createBounty(String playerName, String playerRace, String playerClass, String reward) {
        Bounty b = null;
        if (!playerClass.trim().isEmpty() && !playerRace.trim().isEmpty()) {
            b = Bounty.builder()
                    .playerName(playerName)
                    .playerRace(PLAYER_RACE.valueOf(playerRace.toUpperCase()))
                    .playerClass(PLAYER_CLASS.valueOf(playerClass.toUpperCase()))
                    .reward(reward)
                    .build();
        }
        if (playerClass.trim().isEmpty() || playerRace.trim().isEmpty()) {
            b = Bounty.builder()
                    .playerName(playerName)
                    .playerRace(PLAYER_RACE.UNKNOWN)
                    .playerClass(PLAYER_CLASS.UNKNOWN)
                    .reward(reward)
                    .build();
        }
        return bountyRepository.save(b);

    }
}

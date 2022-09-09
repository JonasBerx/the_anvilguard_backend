package com.anvilguard.the_anvilguard_backend.service;

import com.anvilguard.the_anvilguard_backend.model.Bounty;
import com.anvilguard.the_anvilguard_backend.model.PLAYER_CLASS;
import com.anvilguard.the_anvilguard_backend.model.PLAYER_RACE;
import com.anvilguard.the_anvilguard_backend.model.Player;
import com.anvilguard.the_anvilguard_backend.repository.BountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BountyService {

    @Autowired
    private BountyRepository bountyRepository;

    @Autowired
    private PlayerService playerService;

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
        Player p = playerService.findPlayerByPlayerName(playerName);

        boolean b1 = !playerClass.trim().isEmpty() && !playerRace.trim().isEmpty();
        boolean b2 = playerClass.trim().isEmpty() || playerRace.trim().isEmpty();
        if (p != null) {
            b = buildBounty(playerName, playerRace, playerClass, reward, b, p, b1, b2);
            return bountyRepository.save(b);
        } else {
            p = playerService.createPlayer(playerName, false);

            b = buildBounty(playerName, playerRace, playerClass, reward, b, p, b1, b2);
            return bountyRepository.save(b);
        }
    }

    private Bounty buildBounty(String playerName, String playerRace, String playerClass, String reward, Bounty b, Player p, boolean b1, boolean b2) {
        if (b1) {
            b = Bounty.builder()
                    .playerName(playerName)
                    .player(p)
                    .playerRace(PLAYER_RACE.valueOf(playerRace.toUpperCase()))
                    .playerClass(PLAYER_CLASS.valueOf(playerClass.toUpperCase()))
                    .reward(reward)
                    .build();
        }
        if (b2) {
            b = Bounty.builder()
                    .playerName(playerName)
                    .player(p)
                    .playerRace(PLAYER_RACE.UNKNOWN)
                    .playerClass(PLAYER_CLASS.UNKNOWN)
                    .reward(reward)
                    .build();
        }
        return b;
    }

    public Bounty completeBounty(String playerName) {
        Bounty b = bountyRepository.findByPlayerName(playerName).orElse(null);
        System.out.println("test");
        if (b != null) {
            System.out.println(b.isCompleted());
            b.setCompleted(true);
            System.out.println(b.isCompleted());
            return bountyRepository.save(b);
        }
        return null;
    }
}

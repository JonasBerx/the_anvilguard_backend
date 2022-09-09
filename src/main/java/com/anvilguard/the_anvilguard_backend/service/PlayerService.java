package com.anvilguard.the_anvilguard_backend.service;

import com.anvilguard.the_anvilguard_backend.model.Player;
import com.anvilguard.the_anvilguard_backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player findPlayerByPlayerName(String playerName) {
        return playerRepository.findByPlayerName(playerName).orElse(null);
    }

    public List<Player> findAllHostilePlayers() {
        return playerRepository.findAllByFriendly(false).orElseThrow();
    }

    public Player createPlayer(String playername, boolean friendly) {
         Player p = Player.builder()
                .playerName(playername)
                .friendly(friendly)
                .build();
        return playerRepository.save(p);
    }
}

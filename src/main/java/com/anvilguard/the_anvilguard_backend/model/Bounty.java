package com.anvilguard.the_anvilguard_backend.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="bounties")
public class Bounty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "player_name")
    private String playerName;

    @Enumerated(EnumType.STRING)
    @Nullable
    private PLAYER_CLASS playerClass;

    @Enumerated(EnumType.STRING)
    @Nullable
    private PLAYER_RACE playerRace;

    @Column(name = "reward")
    @Nullable
    private String reward;
}

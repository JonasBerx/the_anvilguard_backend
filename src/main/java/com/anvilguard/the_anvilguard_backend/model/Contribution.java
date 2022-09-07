package com.anvilguard.the_anvilguard_backend.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="contributions")
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "donator_id", nullable = false)
//    private Item item;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "player_id", nullable = false)
//    private Player player;

    @Column(name = "amount")
    private int amount;
}

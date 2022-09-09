package com.anvilguard.the_anvilguard_backend.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TRANSACTION_TYPE type;

    @Column(name = "amount")
    private float amount;

    @ManyToOne(targetEntity = Player.class)
    private Player player;
}

package com.anvilguard.the_anvilguard_backend.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionCreationRequestDto {
    public String type;
    public float amount;
    public String playerName;
}

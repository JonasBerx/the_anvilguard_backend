package com.anvilguard.the_anvilguard_backend.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BountyCreationRequestDto {
    public String playerName;
    public String playerRace;
    public String playerClass;
    public String reward;
}

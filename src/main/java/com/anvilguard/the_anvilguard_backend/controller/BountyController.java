package com.anvilguard.the_anvilguard_backend.controller;

import com.anvilguard.the_anvilguard_backend.controller.dto.BountyCreationRequestDto;
import com.anvilguard.the_anvilguard_backend.model.Bounty;
import com.anvilguard.the_anvilguard_backend.service.BountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bounty")
public class BountyController {

    @Autowired
    private BountyService bountyService;

    @PostMapping
    public ResponseEntity<Bounty> createBounty(@RequestBody BountyCreationRequestDto bountyCreationRequestDto) {
        Bounty b = bountyService.createBounty(bountyCreationRequestDto.getPlayerName(), bountyCreationRequestDto.getPlayerRace(), bountyCreationRequestDto.getPlayerClass(), bountyCreationRequestDto.getReward());

        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Bounty>> getAllBounties() {
        List<Bounty> bounties;

        bounties = bountyService.findAll();
        return new ResponseEntity<>(bounties, HttpStatus.OK);
    }

    @GetMapping("/complete/{playerName}")
    public ResponseEntity<Bounty> completeBounty(@PathVariable String playerName) {
        System.out.println("TEST");
        return new ResponseEntity<>(bountyService.completeBounty(playerName), HttpStatus.OK);
    }
}

package com.fabhotels.round2.controller;

import com.fabhotels.round2.dto.EndorsementResponseDto;
import com.fabhotels.round2.dto.SkillEndorsementsDto;
import com.fabhotels.round2.service.EndorsementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/endorsements")
public class EndorsementController {

    @Autowired
    private EndorsementService endorsementService;

    @GetMapping("/{userId}")
    public List<SkillEndorsementsDto> getEndorsements(@PathVariable String userId) {
        return endorsementService.getEndorsementsForUser(userId);
    }

    @PostMapping
    public EndorsementResponseDto postEndorsement(@RequestParam String reviewee,
                                                  @RequestParam String reviewer,
                                                  @RequestParam String skill,
                                                  @RequestParam Float score) {
        return endorsementService.endorseUser(reviewee, reviewer, skill, score);
    }
}


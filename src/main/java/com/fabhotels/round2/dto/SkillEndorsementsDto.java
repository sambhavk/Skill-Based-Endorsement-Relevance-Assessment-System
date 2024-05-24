package com.fabhotels.round2.dto;

import com.fabhotels.round2.domain.Endorsement;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SkillEndorsementsDto {
    private String skill;
    private List<EndorsementDto> endorsements;

    public SkillEndorsementsDto(String skill, List<Endorsement> endorsements) {
        this.skill = skill;
        this.endorsements = endorsements.stream()
                .map(EndorsementDto::new)
                .collect(Collectors.toList());
    }
}

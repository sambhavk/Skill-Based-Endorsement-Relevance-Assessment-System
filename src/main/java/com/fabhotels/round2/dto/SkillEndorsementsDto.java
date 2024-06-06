package com.fabhotels.round2.dto;

import lombok.Data;

import java.util.List;

@Data
public class SkillEndorsementsDto {
    private String skill;
    private List<EndorsementDto> endorsements;

    public SkillEndorsementsDto(String skill, List<EndorsementDto> endorsements) {
        this.skill = skill;
        this.endorsements = endorsements;
    }
}

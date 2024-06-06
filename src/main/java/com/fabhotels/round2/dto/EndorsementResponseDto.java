package com.fabhotels.round2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EndorsementResponseDto {
    private String skill;
    private Float actualScore;
    private Float adjustedScore;
    private String reason;

    public EndorsementResponseDto(String skill, Float actualScore, Float adjustedScore, String reason) {
        this.skill = skill;
        this.actualScore = actualScore;
        this.adjustedScore = adjustedScore;
        this.reason = reason;
    }
}

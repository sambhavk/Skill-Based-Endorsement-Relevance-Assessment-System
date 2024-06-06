package com.fabhotels.round2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndorsementDto {
    private String endorser;
    private String actualScore;
    private String systemCalculatedWeight;
    private String reason;
    private String skill;
}

package com.fabhotels.round2.dto;

import com.fabhotels.round2.domain.Endorsement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndorsementDto {
    private Long endorser;
    private Float actualScore;
    private Float systemCalculatedWeight;
    private String reason;

    public EndorsementDto(Endorsement endorsement) {
        this.endorser = endorsement.getEndorser();
        this.actualScore = endorsement.getActualScore();
        this.systemCalculatedWeight = endorsement.getSystemCalculatedWeight();
        this.reason = endorsement.getReason();
    }
}

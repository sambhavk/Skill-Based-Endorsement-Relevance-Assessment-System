package com.fabhotels.round2.dto;

import lombok.Data;

@Data
public class EndorsementRequest {
    private Long revieweeUserID;
    private Long reviewerUserID;
    private String skill;
    private Long score;
}

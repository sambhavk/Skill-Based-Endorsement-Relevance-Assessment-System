package com.fabhotels.round2.utils;

import com.fabhotels.round2.dto.EndorsementResponseDto;

public interface Cohort {
    float weightage(String skill, String reviewer, String reviewee, EndorsementResponseDto endorsementResponseDto);
}

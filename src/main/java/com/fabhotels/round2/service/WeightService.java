package com.fabhotels.round2.service;

import com.fabhotels.round2.dto.EndorsementResponseDto;
import com.fabhotels.round2.utils.Cohort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightService {

    @Value(value = "${hop-weight}")
    public static float hopWeight;
    @Autowired
    private List<Cohort> cohorts;

    public float calculateWeight(String skill, String reviewer, String reviewee, EndorsementResponseDto endorsementResponseDto){
        return cohorts
                .stream()
                .map(cohort -> cohort.weightage(skill, reviewer, reviewee, endorsementResponseDto))
                .reduce((w1, w2) -> w1 * w2)
                .get();
    }

}

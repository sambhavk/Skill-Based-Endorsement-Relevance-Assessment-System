package com.fabhotels.round2.service;

import com.fabhotels.round2.domain.Endorsement;
import com.fabhotels.round2.domain.UserProfile;
import com.fabhotels.round2.utils.Cohort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightService {
    @Autowired
    private List<Cohort> cohorts;

    public float calculateWeight(UserProfile reviewee, UserProfile reviewer, String skill, Endorsement endorsement){
        return cohorts
                .stream()
                .map(cohort -> cohort.weightage(reviewee, reviewer, skill, endorsement))
                .reduce((w1, w2) -> w1 * w2)
                .get();
    }

}

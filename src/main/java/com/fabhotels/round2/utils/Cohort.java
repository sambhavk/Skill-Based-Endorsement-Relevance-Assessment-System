package com.fabhotels.round2.utils;

import com.fabhotels.round2.domain.Endorsement;
import com.fabhotels.round2.domain.UserProfile;

public interface Cohort {
    float weightage(UserProfile reviewee, UserProfile reviewer, String skill, Endorsement endorsement);
}

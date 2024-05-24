package com.fabhotels.round2.utils;

import com.fabhotels.round2.domain.Endorsement;
import com.fabhotels.round2.domain.UserExperience;
import com.fabhotels.round2.domain.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CoWorkerCohort implements Cohort {
    @Override
    public float weightage(UserProfile reviewee, UserProfile reviewer, String skill, Endorsement endorsement) {
        UserExperience currentRevieweeExperience =
                reviewee.getExperiences()
                        .stream()
                        .filter(userExperience -> Objects.isNull(userExperience.getEndTime()))
                        .toList().get(0);

        boolean isReviewerCoworkerOfReviewee = reviewer.getExperiences()
                .stream()
                .map(UserExperience::getCompanyName)
                .anyMatch(reviewerCompany -> reviewerCompany.contentEquals(currentRevieweeExperience.getCompanyName()));

        if(isReviewerCoworkerOfReviewee){
            boolean isReviewerPresentCoworkerOfReviewee = reviewer.getExperiences()
                    .stream()
                    .filter(reviewerExperience -> reviewerExperience.getCompanyName().contentEquals(currentRevieweeExperience.getCompanyName()))
                    .anyMatch(reviewerExperience -> Objects.isNull(reviewerExperience.getEndTime()));

            if(isReviewerPresentCoworkerOfReviewee) {
                endorsement.setReason(endorsement.getReason() + " || Co-working Status: Reviewee and reviewer are presently co-worker");
                return 1f;
            }
            else {
                endorsement.setReason(endorsement.getReason() + " || Co-working Status: Reviewee and reviewer were co-workers but not in the present");
                return 0.7f;
            }
        } else {
            endorsement.setReason(endorsement.getReason() + " || Co-working Status: Reviewee and reviewer were never co-workers");
            return 0.5f;
        }
    }
}

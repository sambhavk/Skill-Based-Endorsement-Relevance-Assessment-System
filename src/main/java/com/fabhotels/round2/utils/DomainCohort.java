package com.fabhotels.round2.utils;

import com.fabhotels.round2.domain.Endorsement;
import com.fabhotels.round2.domain.UserExperience;
import com.fabhotels.round2.domain.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class DomainCohort implements Cohort {
    @Override
    public float weightage(UserProfile reviewee, UserProfile reviewer, String skill, Endorsement endorsement) {
        List<UserExperience> currentReviewerExperience = reviewer.getExperiences()
                .stream()
                .filter(userExperience -> Objects.isNull(userExperience.getEndTime()))
                .toList();

        List<UserExperience> pastReviewerExperience = reviewer.getExperiences()
                .stream()
                .filter(userExperience -> Objects.nonNull(userExperience.getEndTime()))
                .toList();

        List<UserExperience> currentRevieweeExperience = reviewee.getExperiences()
                .stream()
                .filter(userExperience -> Objects.isNull(userExperience.getEndTime()))
                .toList();

        String currentRevieweeDomain = currentRevieweeExperience.get(0).getDomain();
        String currentRevieweeSubDomain = currentRevieweeExperience.get(0).getSubDomain();

        String currentReviewerDomain = currentReviewerExperience.get(0).getDomain();
        String currentReviewerSubDomain = currentReviewerExperience.get(0).getSubDomain();

        if(currentReviewerDomain.contentEquals(currentRevieweeDomain) && currentReviewerSubDomain.contentEquals(currentRevieweeSubDomain)) {
            endorsement.setReason(" Domain Status: Current domain and sub-domain of reviewee matched with current domain of reviewer ");
            return 1f;
        }
        else if(currentReviewerDomain.contentEquals(currentRevieweeDomain) && !currentReviewerSubDomain.contentEquals(currentRevieweeSubDomain)){
            endorsement.setReason(" Domain Status: Current domain of reviewee matched with current domain of reviewer but not sub-domain ");
            return 0.8f;
        } else {
            boolean isCurrentRevieweeDomainMatchPastReviewerDomain =
                    pastReviewerExperience
                            .stream()
                            .map(UserExperience::getDomain)
                            .anyMatch(pastReviewerDomain -> Objects.equals(pastReviewerDomain, currentRevieweeDomain));

            boolean isCurrentRevieweeSubDomainMatchPastReviewerSubDomain =
                    pastReviewerExperience
                            .stream()
                            .map(UserExperience::getSubDomain)
                            .anyMatch(pastReviewerDomain -> Objects.equals(pastReviewerDomain, currentRevieweeSubDomain));

            if(isCurrentRevieweeDomainMatchPastReviewerDomain && isCurrentRevieweeSubDomainMatchPastReviewerSubDomain){
                endorsement.setReason(" Domain Status: Current of reviewee matched with Past domain and sub-domain domain of reviewer ");
                return 0.7f;
            }
            else if(isCurrentRevieweeDomainMatchPastReviewerDomain) {
                endorsement.setReason(" Domain Status: Current domain of reviewee matched with past domain of reviewer but not sub-domain ");
                return 0.6f;
            }
            else {
                endorsement.setReason(" Domain Status: Current domain of reviewee does not matched with past domain of reviewer ");
                return 0.5f;
            }
        }
    }
}

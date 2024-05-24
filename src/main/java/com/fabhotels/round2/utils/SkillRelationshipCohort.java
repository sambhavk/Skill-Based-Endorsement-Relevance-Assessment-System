package com.fabhotels.round2.utils;

import com.fabhotels.round2.domain.Endorsement;
import com.fabhotels.round2.domain.UserProfile;
import com.fabhotels.round2.persistence.SkillRelationshipRepository;
import com.fabhotels.round2.persistence.UserSkillRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillRelationshipCohort implements Cohort {
    private final UserSkillRepository userSkillRepository;
    private final SkillRelationshipRepository skillRelationshipRepository;

    public SkillRelationshipCohort(UserSkillRepository userSkillRepository,
                                   SkillRelationshipRepository skillRelationshipRepository) {
        this.userSkillRepository = userSkillRepository;
        this.skillRelationshipRepository = skillRelationshipRepository;
    }

    @Override
    public float weightage(UserProfile reviewee, UserProfile reviewer, String skill, Endorsement endorsement) {
        List<String> reviewerSkills = userSkillRepository.getUserSkills(reviewer.getUid());

        for (String reviewerSkill : reviewerSkills) {
            if (reviewerSkill.equals(skill)) {
                endorsement.setReason(endorsement.getReason() + " || Skill closeness Status: Reviewer has the endorsed skill");
                return 1.0f;
            }

            String relationship = skillRelationshipRepository.getSkillRelationship(skill, reviewerSkill);
            if (relationship != null) {
                switch (relationship) {
                    case "CLOSELY_RELATED":
                        endorsement.setReason(endorsement.getReason() + " || Skill closeness Status: Reviewer has the skills which are closely related to the endorsed skill");
                        return 0.9f;
                    case "DISTANTLY_RELATED":
                        endorsement.setReason(endorsement.getReason() + " || Skill closeness Status: Reviewer has the skills which are distantly related to the endorsed skill");
                        return 0.7f;
                    case "NOT_RELATED":
                        endorsement.setReason(endorsement.getReason() + " || Skill closeness Status: Reviewer has no skills which are related to the endorsed skill");
                        return 0.5f;
                }
            }
        }

        endorsement.setReason(endorsement.getReason() + " || Skill closeness Status: No relationship found between endorsed skill and reviewers skills");
        return 0.5f;
    }
}

package com.fabhotels.round2.utils;

import com.fabhotels.round2.domain.Endorsement;
import com.fabhotels.round2.domain.UserExperience;
import com.fabhotels.round2.domain.UserProfile;
import com.fabhotels.round2.domain.UserSkill;
import com.fabhotels.round2.persistence.UserSkillRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class YearsOfRelevantExperienceCohort implements Cohort {

    private final UserSkillRepository userSkillRepository;

    public YearsOfRelevantExperienceCohort(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    @Override
    public float weightage(UserProfile reviewee, UserProfile reviewer, String skill, Endorsement endorsement) {
        List<UserExperience> experiences = reviewer.getExperiences();
        List<UserSkill> skills = userSkillRepository.findByUserId(reviewer.getUid());

        long totalMonths = experiences.stream()
                .filter(exp -> skills.stream().anyMatch(s -> s.getSkill().equals(skill) && s.getUserExperienceId().equals(exp.getUid())))
                .mapToLong(exp -> {
                    LocalDateTime end = exp.getEndTime() != null ? exp.getEndTime() : LocalDateTime.now();
                    return Duration.between(exp.getStartTime(), end).toDays()/30;
                }).sum();

        float yearsOfExperience = totalMonths / 12.0f;

        if (yearsOfExperience > 5) {
            endorsement.setReason(endorsement.getReason() + " || Years of relevant experience Status: Reviewer has more than 5 years of experience in endorsed skill");
            return 1.0f;
        } else if (yearsOfExperience >= 1) {
            endorsement.setReason(endorsement.getReason() + " || Years of relevant experience Status: Reviewer has between 1 to 5 years of experience in endorsed skill");
            return 0.9f;
        } else if (yearsOfExperience > 0) {
            endorsement.setReason(endorsement.getReason() + " || Years of relevant experience Status: Reviewer has between 0 to 1 years of experience in endorsed skill");
            return 0.7f;
        } else {
            endorsement.setReason(endorsement.getReason() + " || Years of relevant experience Status: Reviewer has no experience in endorsed skill");
            return 0.5f;
        }
    }
}

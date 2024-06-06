package com.fabhotels.round2.utils;

import com.fabhotels.round2.dto.EndorsementResponseDto;
import com.fabhotels.round2.node.Skill;
import com.fabhotels.round2.node.User;
import com.fabhotels.round2.repository.SkillRepository;
import com.fabhotels.round2.repository.UserRepository;
import com.fabhotels.round2.service.WeightService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SkillClosenessCohort implements Cohort {
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillClosenessCohort(SkillRepository skillRepository,
                                UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    @Override
    public float weightage(String endorsedSkill, String reviewer, String reviewee, EndorsementResponseDto endorsementResponseDto) {
        User reviewerObject = userRepository.findById(reviewer).orElseThrow(RuntimeException::new);
        List<String> reviewerSkills = reviewerObject.getSkills().stream().map(Skill::getName).toList();

        if (reviewerSkills.contains(endorsedSkill)) {
            Integer reviewerSkillExperience = reviewerObject.getExperience_years();

            if(reviewerSkillExperience > 5) {
                endorsementResponseDto.setReason(endorsementResponseDto.getReason() + " || Reviewer has the same skill in his/her skill set with skill experience greater than 5");
                return 1f;
            }
            else if (reviewerSkillExperience > 1) {
                endorsementResponseDto.setReason(endorsementResponseDto.getReason() + " || Reviewer has the same skill in his/her skill set with skill experience between 1-5");
                return 0.7f;
            }
            else {
                endorsementResponseDto.setReason(endorsementResponseDto.getReason() + " || Reviewer has the same skill in his/her skill set with skill experience between 0-1");
                return 0.5f;
            }
        }

        int minHops = Integer.MAX_VALUE;

        for(String reviewerSkill: reviewerSkills){
            int currHops = skillRepository.findSkillCloseness(endorsedSkill, reviewerSkill);
            if(currHops < minHops) minHops = currHops;
        }

        if (minHops == 0) endorsementResponseDto.setReason(endorsementResponseDto.getReason() + " || Reviewer does not has the endorsed skill in his/her skill set and has no skills related to it");
        else endorsementResponseDto.setReason(endorsementResponseDto.getReason() + " || Reviewer does not has the endorsed skill in his/her skill set but has skills related to it");

        return minHops == 0 ? 0.1f : minHops * WeightService.hopWeight;
    }
}

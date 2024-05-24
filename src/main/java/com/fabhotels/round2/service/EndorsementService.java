package com.fabhotels.round2.service;

import com.fabhotels.round2.domain.Endorsement;
import com.fabhotels.round2.domain.UserProfile;
import com.fabhotels.round2.dto.EndorsementResponseDto;
import com.fabhotels.round2.dto.SkillEndorsementsDto;
import com.fabhotels.round2.persistence.EndorsementRepository;
import com.fabhotels.round2.persistence.UserProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EndorsementService {
    private final EndorsementRepository endorsementRepository;
    private final UserProfileRepository userProfileRepository;
    private final WeightService weightService;

    public EndorsementService(EndorsementRepository endorsementRepository,
                              UserProfileRepository userProfileRepository,
                              WeightService weightService) {
        this.endorsementRepository = endorsementRepository;
        this.userProfileRepository = userProfileRepository;
        this.weightService = weightService;
    }

    public List<SkillEndorsementsDto> getEndorsementsForUser(Long userId) {
        List<Endorsement> endorsements = endorsementRepository.findByEndorsee(userId);

        return endorsements.stream()
                .collect(Collectors.groupingBy(Endorsement::getSkill))
                .entrySet()
                .stream()
                .map(entry -> new SkillEndorsementsDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Transactional
    public EndorsementResponseDto endorseUser(Long revieweeUserId, Long reviewerUserId, String skill, Float score) {
        UserProfile reviewee = userProfileRepository.findById(revieweeUserId).orElseThrow(() -> new RuntimeException("Reviewee not found"));
        UserProfile reviewer = userProfileRepository.findById(reviewerUserId).orElseThrow(() -> new RuntimeException("Reviewer not found"));

        Endorsement endorsement = new Endorsement();
        float weight = weightService.calculateWeight(reviewee, reviewer, skill, endorsement);
        float adjustedScore = score * weight;

        endorsement.setEndorsee(reviewee.getUid());
        endorsement.setEndorser(reviewer.getUid());
        endorsement.setSkill(skill);
        endorsement.setActualScore(score);
        endorsement.setSystemCalculatedWeight(adjustedScore);
        endorsement.setAudCreatedBy("system");
        endorsement.setAudCreatedDate(LocalDateTime.now());
        endorsement.setAudLastModifiedBy("system");
        endorsement.setAudLastModifiedDate(LocalDateTime.now());

        endorsementRepository.save(endorsement);

        return new EndorsementResponseDto(skill, score, adjustedScore, endorsement.getReason());
    }
}

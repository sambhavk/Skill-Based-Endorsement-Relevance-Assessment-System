package com.fabhotels.round2.service;

import com.fabhotels.round2.dto.EndorsementDto;
import com.fabhotels.round2.dto.EndorsementResponseDto;
import com.fabhotels.round2.dto.SkillEndorsementsDto;
import com.fabhotels.round2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EndorsementService {
    private final WeightService weightService;
    private final UserRepository userRepository;

    public EndorsementService(WeightService weightService,
                              UserRepository userRepository) {
        this.weightService = weightService;
        this.userRepository = userRepository;
    }

    public List<SkillEndorsementsDto> getEndorsementsForUser(String userId) {
        List<Map<String, Object>> rawEndorsements = userRepository.getEndorsementsReceived(userId);

        Map<String, List<EndorsementDto>> groupedBySkill = rawEndorsements.stream()
                .map(e -> new EndorsementDto(
                        (String) e.get("reviewer"),
                        (String) e.get("score"),
                        (String) e.get("systemAdjustedScore"),
                        (String) e.get("reason"),
                        (String) e.get("skill")
                ))
                .collect(Collectors.groupingBy(EndorsementDto::getSkill));

        return groupedBySkill.entrySet().stream()
                .map(entry -> new SkillEndorsementsDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Transactional(value = "transactionManager")
    public EndorsementResponseDto endorseUser(String reviewee, String reviewer, String skill, Float score) {

        EndorsementResponseDto endorsementResponseDto = new EndorsementResponseDto();
        float weight = weightService.calculateWeight(skill, reviewer, reviewee, endorsementResponseDto);
        float adjustedScore = score * weight;
        userRepository.createEndorsement(reviewer, reviewee, score, skill, adjustedScore, endorsementResponseDto.getReason());

        endorsementResponseDto.setSkill(skill);
        endorsementResponseDto.setActualScore(score);
        endorsementResponseDto.setAdjustedScore(adjustedScore);

        return endorsementResponseDto;
    }
}

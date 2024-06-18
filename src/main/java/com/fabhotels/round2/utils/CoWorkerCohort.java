package com.fabhotels.round2.utils;

import com.fabhotels.round2.dto.EndorsementResponseDto;
import com.fabhotels.round2.repository.UserRepository;
import com.fabhotels.round2.service.WeightService;
import org.springframework.stereotype.Component;

@Component
public class CoWorkerCohort implements Cohort {

    private final UserRepository userRepository;

    public CoWorkerCohort(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public float weightage(String skill, String reviewer, String reviewee, EndorsementResponseDto endorsementResponseDto) {
        float coWorkingStatus = (float) userRepository.findCoWorkerStatus(reviewer, reviewee);

        if(coWorkingStatus > 1) {
            endorsementResponseDto.setReason(" Reviewer and reviewee were never co-workers but there are some mutual users between them");
            return (float) Math.pow(WeightService.hopWeight, coWorkingStatus);
        }
        else {
            if(coWorkingStatus == 1f) endorsementResponseDto.setReason(" Reviewer and reviewee are co-workers");
            else if(coWorkingStatus == 0.7f) endorsementResponseDto.setReason(" Reviewer and reviewee were co-workers in the past");
            else endorsementResponseDto.setReason(" Reviewer and reviewee were never co-workers and there are no mutual users between them");
            return coWorkingStatus;
        }
    }
}

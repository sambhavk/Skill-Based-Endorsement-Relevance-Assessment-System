package com.fabhotels.round2.domain;

import lombok.Data;
import jakarta.persistence.*;

@Embeddable
@Data
public class SkillRelationshipId {
    private String skill1;
    private String skill2;
}


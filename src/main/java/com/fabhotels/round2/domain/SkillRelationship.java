package com.fabhotels.round2.domain;

import com.fabhotels.round2.persistence.support.AuditableEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skill_relationship")
public class SkillRelationship extends AuditableEntity {

    @EmbeddedId
    private SkillRelationshipId id;

    @Column(name = "relation", nullable = false)
    private String relation;
}

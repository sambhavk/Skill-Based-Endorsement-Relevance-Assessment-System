package com.fabhotels.round2.domain;

import com.fabhotels.round2.persistence.support.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_skills")
public class UserSkill extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_skills_seq_gen")
    @SequenceGenerator(name = "user_skills_seq_gen", sequenceName = "seq_user_skills", allocationSize = 1)
    private Long uid;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_experience_id", nullable = false)
    private Long userExperienceId;

    @Column(name = "skill", nullable = false)
    private String skill;
}

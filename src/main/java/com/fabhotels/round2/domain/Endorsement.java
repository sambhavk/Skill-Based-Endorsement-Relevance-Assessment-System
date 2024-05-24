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
@Table(name = "endorsements")
public class Endorsement extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endorsements_seq_gen")
    @SequenceGenerator(name = "endorsements_seq_gen", sequenceName = "seq_endorsements", allocationSize = 1)
    private Long uid;

    @Column(name = "endorser", nullable = false)
    private Long endorser;

    @Column(name = "endorsee", nullable = false)
    private Long endorsee;

    @Column(name = "skill", nullable = false)
    private String skill;

    @Column(name = "actual_score", nullable = false)
    private Float actualScore;

    @Column(name = "system_calculated_weight", nullable = false)
    private Float systemCalculatedWeight;

    @Column(name = "reason", nullable = false)
    private String reason;

}

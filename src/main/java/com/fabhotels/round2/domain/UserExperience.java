package com.fabhotels.round2.domain;

import com.fabhotels.round2.persistence.support.AuditableEntity;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_experience")
public class UserExperience extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_experience_seq_gen")
    @SequenceGenerator(name = "user_experience_seq_gen", sequenceName = "seq_user_experience", allocationSize = 1)
    private Long uid;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "sub_domain", nullable = false)
    private String subDomain;
}

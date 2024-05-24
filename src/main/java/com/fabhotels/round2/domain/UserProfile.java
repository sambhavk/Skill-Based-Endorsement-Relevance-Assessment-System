package com.fabhotels.round2.domain;

import com.fabhotels.round2.persistence.support.AuditableEntity;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_profile")
public class UserProfile extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profile_seq_gen")
    @SequenceGenerator(name = "user_profile_seq_gen", sequenceName = "seq_user_profile", allocationSize = 1)
    private Long uid;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserExperience> experiences;
}

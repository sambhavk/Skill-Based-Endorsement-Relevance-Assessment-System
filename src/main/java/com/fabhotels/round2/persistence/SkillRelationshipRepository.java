package com.fabhotels.round2.persistence;

import com.fabhotels.round2.domain.SkillRelationship;
import com.fabhotels.round2.domain.SkillRelationshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRelationshipRepository extends JpaRepository<SkillRelationship, SkillRelationshipId> {
    @Query(value = "select sr.relation from skill_relationship sr " +
            "where (sr.skill_1 = :skill1 AND sr.skill_2 = :skill2) " +
            "OR (sr.skill_1 = :skill2 AND sr.skill_2 = :skill1)", nativeQuery = true)
    String getSkillRelationship(String skill1, String skill2);
}

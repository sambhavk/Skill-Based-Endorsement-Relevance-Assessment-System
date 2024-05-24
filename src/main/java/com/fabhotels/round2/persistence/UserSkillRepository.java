package com.fabhotels.round2.persistence;

import com.fabhotels.round2.domain.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    @Query(value = "select us.skill from user_skills us where us.user_id = :userId", nativeQuery = true)
    List<String> getUserSkills(Long userId);

    List<UserSkill> findByUserId(Long userId);
}

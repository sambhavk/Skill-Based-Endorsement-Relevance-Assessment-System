package com.fabhotels.round2.repository;

import com.fabhotels.round2.node.Skill;
import com.fabhotels.round2.node.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface UserRepository extends Neo4jRepository<User, String> {

    @Query("MATCH (u:User)-[:HAS_SKILL]->(s:Skill) WHERE u.userId = $userId RETURN s.skillId AS skillId, s.name AS name")
    Set<Skill> findSkillsByUserId(String userId);

    @Query("""
            OPTIONAL MATCH (u1:User {userId: $reviewer})
            OPTIONAL MATCH (u2:User {userId: $reviewee})
            WITH u1, u2
            OPTIONAL MATCH present = (u1)-[:COWORKER {timeline: 'PRESENT'}]->(u2)
            OPTIONAL MATCH past = (u1)-[:COWORKER {timeline: 'PAST'}]->(u2)
            WITH u1, u2, present, past
            OPTIONAL MATCH degreePath = shortestPath((u1)-[:COWORKER*..10]-(u2))
            RETURN CASE
                WHEN present IS NOT NULL THEN 1
                WHEN past IS NOT NULL THEN 0.7
                WHEN degreePath IS NOT NULL AND length(degreePath) > 1 THEN length(degreePath)
                ELSE 0.1
            END AS coWorkingStatus
            """)
    double findCoWorkerStatus(String reviewer, String reviewee);

    @Query("""
            OPTIONAL MATCH (u1:User {userId: $reviewer})
            OPTIONAL MATCH (u2:User {userId: $reviewee})
            WITH u1, u2
            OPTIONAL MATCH (u1)-[endorsement:ENDORSES {skill: $skill}]->(u2)
            WITH u1, u2, endorsement
            FOREACH (ignoreMe IN CASE WHEN u1 IS NOT NULL AND u2 IS NOT NULL AND endorsement IS NULL THEN [1] ELSE [] END |
                CREATE (u1)-[:ENDORSES {score: $score, skill: $skill, systemAdjustedScore: $systemAdjustedScore, reason: $reason}]->(u2)
            )
            """)
    void createEndorsement(String reviewer, String reviewee, Float score, String skill, Float systemAdjustedScore, String reason);

    @Query("""
          MATCH (u:User {userId: $userId})
          OPTIONAL MATCH (otherUser)-[reverseEndorsement:ENDORSES]->(u)
          RETURN {
              reviewer: otherUser.userId,
              score: reverseEndorsement.score,
              systemAdjustedScore: reverseEndorsement.systemAdjustedScore,
              skill: reverseEndorsement.skill,
              reason: reverseEndorsement.reason
          } AS endorsements
          """)
    List<Map<String, Object>> getEndorsementsReceived(String userId);
}

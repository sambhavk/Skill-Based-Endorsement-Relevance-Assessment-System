package com.fabhotels.round2.repository;

import com.fabhotels.round2.node.Skill;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends Neo4jRepository<Skill, String> {

    @Query(value = """
              OPTIONAL MATCH (s1:Skill {name: $s1})
              OPTIONAL MATCH (s2:Skill {name: $s2})
              WITH s1, s2
              OPTIONAL MATCH p = shortestPath((s1)-[:RELATED_TO*..10]-(s2))
              RETURN CASE
                  WHEN s1 IS NULL OR s2 IS NULL OR p IS NULL THEN 0
                  ELSE length(p)
              END AS hops
            """)
    Integer findSkillCloseness(String s1, String s2);
}

package com.fabhotels.round2.node;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
@Data
public class User {
    @Id
    private String userId;
    private String name;
    private Integer experience_years;

    @Relationship(type = "HAS_SKILL")
    private Set<Skill> skills;
}

package com.fabhotels.round2.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node(labels = "Skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    private String skillId;
    private String name;
}

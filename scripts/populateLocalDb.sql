
CREATE (Alice:User {userId: 'u1', name: 'Alice', experience_years: 10}),
        (Bob:User {userId: 'u2', name: 'Bob', experience_years: 5}),

         (pl:Category {categoryId: 'c1', name : 'Programming Languages'}),
         (fw:Category {categoryId: 'c1', name : 'Frameworks'}),
         (db:Category {categoryId: 'c1', name : 'Databases'}),
         (tl:Category {categoryId: 'c1', name : 'Tools'}),

         (java:Skill {skillId: 's1', name: 'Java'}),
        	(scala:Skill {skillId: 's3', name: 'Scala'}),
        	(python:Skill {skillId: 's4', name: 'Python'}),
        	(javascript:Skill {skillId: 's14', name: 'Javascript'}),

         (spring:Skill {skillId: 's5', name: 'Spring Boot'}),
         (django:Skill {skillId: 's6', name: 'Django'}),
         (react:Skill {skillId: 's7', name: 'ReactJS'}),
         (akka:Skill {skillId: 's15', name: 'Akka'}),

         (neo4j:Skill {skillId: 's8', name : 'Neo4j'}),
         (mysql:Skill {skillId: 's9', name : 'MySQL'}),
         (mongo:Skill {skillId: 's10', name : 'MongoDB'}),

         (git:Skill {skillId: 's11', name : 'Git'}),
         (docker:Skill {skillId: 's12', name : 'Docker'}),
         (jenkins:Skill {skillId: 's13', name : 'Jenkins'}),

         (spring)-[:RELATED_TO]->(java),
         (java)-[:RELATED_TO]->(spring),
         (django)-[:RELATED_TO]->(python),
         (python)-[:RELATED_TO]->(django),
         (react)-[:RELATED_TO]->(javascript),
         (javascript)-[:RELATED_TO]->(react),
         (akka)-[:RELATED_TO]->(scala),
         (scala)-[:RELATED_TO]->(akka),

        (pl)-[:INCLUDES]->(java),
         (pl)-[:INCLUDES]->(scala),
         (pl)-[:INCLUDES]->(python),
         (pl)-[:INCLUDES]->(javascript),

        (fw)-[:INCLUDES]->(spring),
         (fw)-[:INCLUDES]->(django),
         (fw)-[:INCLUDES]->(react),

        (db)-[:INCLUDES]->(neo4j),
         (db)-[:INCLUDES]->(mysql),
         (db)-[:INCLUDES]->(mongo),

        (tl)-[:INCLUDES]->(git),
         (tl)-[:INCLUDES]->(docker),
         (tl)-[:INCLUDES]->(jenkins),

        (Alice)-[:HAS_SKILL]->(java),
        (Alice)-[:HAS_SKILL]->(react),
        (Alice)-[:HAS_SKILL]->(docker),
        (Bob)-[:HAS_SKILL]->(scala),
        (Bob)-[:HAS_SKILL]->(akka),
        (Bob)-[:HAS_SKILL]->(docker),

        (Alice)-[:COWORKER {timeline: 'PRESENT'}]->(Bob),
        (Bob)-[:COWORKER {timeline: 'PRESENT'}]->(Alice),

        (Alice)-[:ENDORSES {skill: 'Docker', score: 8.5, systemAdjustedScore: 7.87623, reason: 'test entry'}]->(Bob);
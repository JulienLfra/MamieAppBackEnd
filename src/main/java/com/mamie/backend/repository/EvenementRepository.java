package com.mamie.backend.repository;

import com.mamie.backend.model.Evenement;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvenementRepository extends Neo4jRepository<Evenement, Long> {

    @Query("MATCH (famille:Famille), (event:Evenement) WHERE ID(famille)=$id AND (famille)-[:PARTICIPE]->(event) RETURN event")
    List<Evenement> findEvenementByFamille(@Param("id") int id);
}

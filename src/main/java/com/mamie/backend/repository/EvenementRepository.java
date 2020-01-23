package com.mamie.backend.repository;

import com.mamie.backend.model.Evenement;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvenementRepository extends Neo4jRepository<Evenement, Long> {

    @Query("MATCH (user:Personne), (famille:Famille), (event:Evenement) WHERE user.mail=~$mail AND (user)-[:IN]->(famille) AND (famille)-[:PARTICIPE]->(event) RETURN event")
    List<Evenement> findEvenementByMailUser(@Param("mail") String mail);
}

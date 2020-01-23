package com.mamie.backend.repository;

import com.mamie.backend.model.Famille;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilleRepository extends Neo4jRepository<Famille, Long> {

    Famille findByNom(@Param("nom") String nom);
    boolean existsByNom (@Param("nom") String nom);

    @Query("MATCH (user:Personne), (famille:Famille) WHERE user.mail=~$mail AND (user)-[:IN]->(famille) RETURN famille")
    List<Famille> findByMail(@Param("mail") String mail);
}

package com.mamie.backend.repository;

import com.mamie.backend.model.Personne;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface PersonneRepository extends Neo4jRepository<Personne, Long> {

    Personne findByNom(@Param("nom") String nom);
    Personne findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);
    boolean existsByNom (@Param("nom") String nom);
    Personne findByMail(@Param("mail") String mail);
@Query("MATCH (user:Personne), (membre:Personne), (famille:Famille) WHERE ID(user)=12 AND (user)-[:IN]->(famille) AND (membre)-[:IN]->(famille) RETURN membre")
    Personne findByFamille(String famille);
}
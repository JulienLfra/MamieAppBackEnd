package com.mamie.backend.repository;

import com.mamie.backend.model.Personne;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonneRepository extends Neo4jRepository<Personne, Long> {

    Personne findByNom(@Param("nom") String nom);

    Personne findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    boolean existsByNom(@Param("nom") String nom);

    Personne findByMail(@Param("mail") String mail);

    @Query("MATCH (user:Personne) WHERE user.mail=~$mail SET user.photo=$photo")
    Personne modifyPhotoPersonneByMail(@Param("mail") String mail, @Param("photo") String photo);

    @Query("MATCH (user:Personne), (membre:Personne), (famille:Famille) WHERE user.mail=~$mail AND (user)-[:IN]->(famille) AND (membre)-[:IN]->(famille) RETURN membre")
    List<Personne> findMembreFamilleByUserMail(@Param("mail") String mail);

    @Query("MATCH (user:Personne) WHERE user.mail=~$personne.mail SET user=$personne")
    void modifyInfosPersonne(Personne personne);

    @Query("MATCH (user:Personne), (conjoint:Personne) WHERE user.mail=~$mail AND (user)-[:Mariage]-(conjoint) RETURN conjoint")
    List<Personne> findConjoint(@Param("mail") String mail);


    @Query("MATCH (user:Personne), (famille:Famille), (membre:Personne) WHERE user.mail=~$mail AND famille.nom=$nomFamille AND (user)-[:IN]->(famille) AND (membre)-[:IN]->(famille) RETURN membre")
    List<Personne> findByNomFamille(@Param("mail") String mail, @Param("nomFamille") String nomFamille);

    @Query("MATCH (famille:Famille), (membre:Personne) WHERE ID(famille)=$id AND (membre)-[:IN]->(famille) RETURN membre")
    List<Personne> findById_Famille(@Param("id") int id);

    @Query("MATCH (user:Personne), (sibling:Personne) WHERE user.mail=~$mail AND (user)-[:Sibling]-(sibling) RETURN sibling")
    List<Personne> findSiblings(@Param("mail") String mail);

    @Query("MATCH (user:Personne), (parent:Personne) WHERE user.mail=~$mail AND (user)-[:Enfant_de]->(parent) RETURN parent")
    List<Personne> findParents(@Param("mail") String mail);

    @Query("MATCH (user:Personne), (enfant:Personne) WHERE user.mail=~$mail AND (user)<-[:Enfant_de]-(enfant) RETURN enfant")
    List<Personne> findEnfants(@Param("mail") String mail);

}
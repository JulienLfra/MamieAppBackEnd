package com.mamie.backend.repository;

import com.mamie.backend.model.Famille;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface FamilleRepository extends Neo4jRepository<Famille, Long> {

    Famille findByNom(@Param("nom") String nom);
    boolean existsByNom (@Param("nom") String nom);
}

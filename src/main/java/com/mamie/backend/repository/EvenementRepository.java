package com.mamie.backend.repository;

import com.mamie.backend.model.Evenement;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EvenementRepository extends Neo4jRepository<Evenement, Long> {
}

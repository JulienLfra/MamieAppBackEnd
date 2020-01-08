package com.mamie.backend.repository;

import com.mamie.backend.model.Famille;
import org.springframework.data.repository.CrudRepository;

public interface FamilleRepository extends CrudRepository<Famille, Long> {
}

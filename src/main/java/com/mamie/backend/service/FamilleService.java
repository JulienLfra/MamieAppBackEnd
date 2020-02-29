package com.mamie.backend.service;

import com.mamie.backend.model.Famille;
import com.mamie.backend.repository.FamilleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamilleService {


    private final FamilleRepository familleRepository;

    public FamilleService(FamilleRepository familleRepository) {
        this.familleRepository = familleRepository;
    }

    @Transactional(readOnly = true)
    public Famille findByNom(String nom) {
        return familleRepository.findByNom(nom);
    }
}

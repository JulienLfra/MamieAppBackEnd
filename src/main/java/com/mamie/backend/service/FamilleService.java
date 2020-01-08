package com.mamie.backend.service;

import com.mamie.backend.model.Famille;
import com.mamie.backend.repository.FamilleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamilleService {

    private final static Logger log = LoggerFactory.getLogger(FamilleService.class);

    private final FamilleRepository familleRepository;
    public FamilleService(FamilleRepository familleRepository) {
        this.familleRepository = familleRepository;
    }

    @Transactional(readOnly = true)
    public Famille findByName(String name) {
        return familleRepository.findByName(name);
    }
}

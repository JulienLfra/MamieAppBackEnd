package com.mamie.backend.controller;

import com.mamie.backend.model.Evenement;
import com.mamie.backend.repository.EvenementRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EvenementController {

    private final EvenementRepository evenementRepository;

    public EvenementController(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    @GetMapping(path = "/evenements", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Evenement> getEvenements() {

        Iterable<Evenement> f = evenementRepository.findAll();
        List<Evenement> evenements = new ArrayList<>();
        for (Evenement evenement : f) {
            evenements.add(evenement);
        }
        return evenements;
    }
}

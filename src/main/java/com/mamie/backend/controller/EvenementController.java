package com.mamie.backend.controller;

import com.mamie.backend.model.Evenement;
import com.mamie.backend.repository.EvenementRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(path = "/evenementsFamille", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Evenement> getEvenementsByFamily() {

        Iterable<Evenement> f = evenementRepository.findAll();
        List<Evenement> evenements = new ArrayList<>();
        for (Evenement evenement : f) {
            evenements.add(evenement);
        }
        return evenements;
    }

    @PutMapping(path ="/evenement")
    public void addEvenement(@RequestBody Evenement evenement) {
        evenementRepository.save(evenement);
    }


    @PostMapping(path ="/evenement")
    public void addPostEvenement(@RequestBody Evenement evenement) {
        evenementRepository.save(evenement);
    }


    @DeleteMapping(path ="/evenement")
    public void deleteEvenement(@RequestBody Evenement evenement) {
        evenementRepository.delete(evenement);
    }
}

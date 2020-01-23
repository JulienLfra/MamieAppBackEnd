package com.mamie.backend.controller;

import com.mamie.backend.model.Evenement;
import com.mamie.backend.repository.EvenementRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
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


    @GetMapping(path = "/evenementsById", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Evenement> getEvenementsByUserMail(@RequestParam int id) {

        return evenementRepository.findById((long) id);
    }


    @GetMapping(path = "/evenementsByUserMail", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Evenement> getEvenementsByUserMail(@RequestParam String mail) {

        return evenementRepository.findEvenementByMailUser(mail);
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

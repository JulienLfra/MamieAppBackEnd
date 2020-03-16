package com.mamie.backend.controller;

import com.mamie.backend.model.Evenement;
import com.mamie.backend.repository.EvenementRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<Evenement> getEvenementsById(@RequestParam int id) {
        return evenementRepository.findById(id);
    }


    @GetMapping(path = "/evenementsByUserMail", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Evenement> getEvenementsByUserMail(@RequestParam String mail) {

        return evenementRepository.findEvenementByMailUser(mail);
    }

    @PutMapping(path = "/evenement", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public void addEvenement(@RequestBody Evenement evenementRecu) {
        Evenement evenement = new Evenement();
        evenement.setDate(evenementRecu.getDate());
        evenement.setNom(evenementRecu.getNom());
        evenement.setFamille(evenementRecu.getFamille());
        evenement.setLieu(evenementRecu.getLieu());
        evenement.setPhoto(evenementRecu.getPhoto());
        evenementRepository.save(evenement);
    }


    @PostMapping(path = "/evenement", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public void addPostEvenement(@RequestBody Evenement evenementRecu) {
        Evenement evenement = new Evenement();
        evenement.setNom(evenementRecu.getNom());
        evenement.setDate(evenementRecu.getDate());
        evenement.setFamille(evenementRecu.getFamille());
        evenement.setLieu(evenementRecu.getLieu());
        evenement.setPhoto(evenementRecu.getPhoto());
        evenementRepository.save(evenement);
    }


    @DeleteMapping(path = "/evenement")
    public void deleteEvenement(@RequestBody Evenement evenement) {
        evenementRepository.delete(evenement);
    }
}

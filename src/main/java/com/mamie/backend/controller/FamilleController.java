package com.mamie.backend.controller;

import com.mamie.backend.exception.famille.FamilleNonSupprimeeException;
import com.mamie.backend.exception.famille.ObtenirFamilleException;
import com.mamie.backend.exception.famille.ObtenirFamillesException;
import com.mamie.backend.model.Famille;
import com.mamie.backend.repository.FamilleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FamilleController {


    private final FamilleRepository familleRepository;

    public FamilleController(FamilleRepository familleRepository) {
        this.familleRepository = familleRepository;
    }


    @ExceptionHandler({ObtenirFamillesException.class})
    @GetMapping(path = "/familles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Famille> getFamilles() {

        Iterable<Famille> f = familleRepository.findAll();
        List<Famille> familles = new ArrayList<>();
        for (Famille famille : f) {
            familles.add(famille);
        }
        return familles;
    }

    @GetMapping(path = "/familleNom", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Famille> getFamilleNom(@RequestParam String nom) {

        List<Famille> famille = new ArrayList<>();
        Famille result = familleRepository.findByNom(nom);
        if (result == null) {
            throw new ObtenirFamilleException(nom);
        }
        famille.add(familleRepository.findByNom(nom));
        return famille;
    }

    @GetMapping(path = "/familleMail", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Famille> getFamillesMail(@RequestParam String mail) {


        List<Famille> result = (List<Famille>) familleRepository.findByMail(mail);
        if (result == null) {
            throw new ObtenirFamilleException(mail);
        }
        return result;
    }

    @PostMapping(path = "/famille")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPostFamille(@RequestBody Famille famille) {
        familleRepository.save(famille);
    }

    @PutMapping(path = "/famille")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFamille(@RequestBody Famille famille) {
        familleRepository.save(famille);
    }


    @DeleteMapping(path = "/famille")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFamille(@RequestBody Famille famille) {
        if (!isSaveInDataBase(famille)) {
            throw new ObtenirFamilleException(famille.getNom());
        }

        Famille familleToDelete = familleRepository.findByNom(famille.getNom());

        familleRepository.delete(familleToDelete);

        if (isSaveInDataBase(famille)) {
            throw new FamilleNonSupprimeeException(famille.getNom());
        }
    }


    private boolean isSaveInDataBase(Famille famille) {
        return familleRepository.existsByNom(famille.getNom());
    }
}

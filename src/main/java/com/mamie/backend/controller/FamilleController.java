package com.mamie.backend.controller;

import com.mamie.backend.model.Famille;
import com.mamie.backend.repository.FamilleRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FamilleController {


    private final FamilleRepository familleRepository;

    public FamilleController(FamilleRepository familleRepository) {
        this.familleRepository = familleRepository;
    }


    @GetMapping(path = "/familles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Famille> getFamilles() {

        Iterable<Famille> f = familleRepository.findAll();
        List<Famille> familles = new ArrayList<>();
        for (Famille famille : f) {
            familles.add(famille);
        }
        return familles;
    }

    @GetMapping(path = "/famille", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Famille> getFamilleNom(@RequestParam String nom) {

        List<Famille> famille = new ArrayList<>();
        famille.add(familleRepository.findByNom(nom));
        return famille;
    }

    @PutMapping(path ="/famille")
    public void addFamille(@RequestBody Famille famille) {
        familleRepository.save(famille);
    }

    @DeleteMapping(path="/famille")
    public void deleteFamille(@RequestBody Famille famille) throws Exception {
        if (!isSaveInDataBase(famille)) {
            throw new Exception("La famille n'existe pas!");
        }

        Famille familleToDelete = familleRepository.findByNom(famille.getNom());

        familleRepository.delete(familleToDelete);

        if(isSaveInDataBase(famille)) {
            throw new Exception("La famille n'a pas été supprimée!");
        }
    }


    private boolean isSaveInDataBase(Famille famille) {
        return familleRepository.existsByNom(famille.getNom());
    }
}

package com.mamie.backend.controller;

import com.mamie.backend.model.Famille;
import com.mamie.backend.model.Personne;
import com.mamie.backend.repository.FamilleRepository;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FamilleController {

    private final Driver driver;

    private final FamilleRepository familleRepository;

    public FamilleController(Driver driver, FamilleRepository familleRepository) {
        this.driver = driver;
        this.familleRepository = familleRepository;
    }


    @GetMapping(path = "/familles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Famille> getFamillesName() {

        Iterable<Famille> f = familleRepository.findAll();
        List<Famille> familles = new ArrayList<>();
        for (Famille famille : f) {
            familles.add(famille);
        }
        return familles;
    }

    @GetMapping(path = "/famille", produces = MediaType.APPLICATION_JSON_VALUE)
    public Famille getPersonneName(@RequestParam String name) {

        return familleRepository.findByName(name);
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

        Famille familleToDelete = familleRepository.findByName(famille.getName());

        familleRepository.delete(familleToDelete);

        if(isSaveInDataBase(famille)) {
            throw new Exception("La famille n'a pas été supprimée!");
        }
    }


    private boolean isSaveInDataBase(Famille famille) {
        return familleRepository.existsByName(famille.getName());
    }
}
